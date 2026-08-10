package com.juliosburger.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliosburger.domain.model.DraftOrderItem
import com.juliosburger.domain.model.ModifierGroup
import com.juliosburger.domain.model.ModifierOption
import com.juliosburger.domain.model.Product
import com.juliosburger.domain.usecase.BuildDraftOrderItemUseCase
import com.juliosburger.domain.usecase.CreateDraftOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class SelectionState(
    val product: Product? = null,
    val currentGroup: ModifierGroup? = null,
    val selectedModifiers: List<ModifierOption> = emptyList(),
    val validationError: String? = null
)

@HiltViewModel
class ProductSelectionViewModel @Inject constructor(
    private val buildDraftOrderItemUseCase: BuildDraftOrderItemUseCase,
    private val createDraftOrderUseCase: CreateDraftOrderUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SelectionState())
    val state: StateFlow<SelectionState> = _state.asStateFlow()

    fun selectProduct(product: Product) {
        _state.value = _state.value.copy(
            product = product,
            currentGroup = null,
            selectedModifiers = emptyList(),
            validationError = null
        )
    }

    fun selectGroup(group: ModifierGroup) {
        _state.value = _state.value.copy(
            currentGroup = group,
            validationError = null
        )
    }

    fun updateSelectedModifiersForGroup(groupId: UUID, modifiers: List<ModifierOption>) {
        val currentState = _state.value
        val existing = currentState.selectedModifiers.filter {
            it.modifierGroupId != groupId
        }
        val merged = existing + modifiers
        _state.value = currentState.copy(
            selectedModifiers = merged,
            validationError = null
        )
    }

    fun getSelectedModifiersForGroup(groupId: UUID): List<ModifierOption> {
        return _state.value.selectedModifiers.filter {
            it.modifierGroupId == groupId
        }
    }

    suspend fun buildDraftOrderItem(): DraftOrderItem? {
        val s = _state.value
        val product = s.product ?: return null
        return try {
            val group = s.currentGroup
            buildDraftOrderItemUseCase(
                BuildDraftOrderItemUseCase.Params(
                    product = product,
                    modifierOptions = s.selectedModifiers,
                    quantity = 1,
                    currentGroupId = group?.id,
                    minSelection = group?.minSelection ?: 0,
                    maxSelection = group?.maxSelection ?: Int.MAX_VALUE
                )
            ).first()
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                validationError = e.message ?: "Error de validación"
            )
            null
        }
    }

    fun createOrderFromSelection(
        customerPhone: String,
        onResult: (success: Boolean, error: String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val item = buildDraftOrderItem()
                if (item == null) {
                    onResult(false, _state.value.validationError ?: "No se pudo construir el item")
                    return@launch
                }
                val params = CreateDraftOrderUseCase.Params(
                    customerPhone = customerPhone,
                    items = listOf(item)
                )
                createDraftOrderUseCase(params).collect {
                    onResult(true, null)
                }
            } catch (e: Exception) {
                onResult(false, e.message ?: "Error al crear pedido")
            }
        }
    }

    fun reset() {
        _state.value = SelectionState()
    }
}
