package com.juliosburger.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliosburger.domain.model.DraftOrderItem
import com.juliosburger.domain.model.Product
import com.juliosburger.domain.model.DraftOrderStatus
import com.juliosburger.domain.usecase.AcceptDraftOrderUseCase
import com.juliosburger.domain.usecase.ConfirmDraftOrderUseCase
import com.juliosburger.domain.usecase.CreateDraftOrderUseCase
import com.juliosburger.domain.usecase.GetDraftOrdersUseCase
import com.juliosburger.domain.usecase.StartCookingUseCase
import com.juliosburger.domain.usecase.CompleteOrderUseCase
import com.juliosburger.presentation.state.OrderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

    @HiltViewModel
    class OrderViewModel @Inject constructor(
        private val createDraftOrderUseCase: CreateDraftOrderUseCase,
        private val getDraftOrdersUseCase: GetDraftOrdersUseCase,
        private val confirmDraftOrderUseCase: ConfirmDraftOrderUseCase,
        private val acceptDraftOrderUseCase: AcceptDraftOrderUseCase,
        private val startCookingUseCase: StartCookingUseCase,
        private val completeOrderUseCase: CompleteOrderUseCase
    ) : ViewModel() {

    private val _state = MutableStateFlow<OrderUiState>(OrderUiState.Loading)
    val state: StateFlow<OrderUiState> = _state.asStateFlow()

    init {
        loadOrders()
    }

    fun loadOrders() {
        loadOrdersByStatus(DraftOrderStatus.DRAFT)
    }

    fun loadOrdersByStatus(status: DraftOrderStatus) {
        viewModelScope.launch {
            _state.value = OrderUiState.Loading
            try {
                getDraftOrdersUseCase(status).collect { result ->
                    _state.value = OrderUiState.Success(result)
                }
            } catch (e: Exception) {
                _state.value = OrderUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun confirmOrder(draftOrderId: String) {
        viewModelScope.launch {
            try {
                confirmDraftOrderUseCase(draftOrderId).collect { result ->
                    result.onSuccess {
                        loadOrders()
                    }.onFailure { error ->
                        _state.value = OrderUiState.Error(error.message ?: "Error al confirmar pedido")
                    }
                }
            } catch (e: Exception) {
                _state.value = OrderUiState.Error(e.message ?: "Error al confirmar pedido")
            }
        }
    }

    fun acceptOrder(draftOrderId: String) {
        viewModelScope.launch {
            try {
                acceptDraftOrderUseCase(draftOrderId).collect { result ->
                    result.onSuccess {
                        loadOrdersByStatus(DraftOrderStatus.PENDING_CASHIER_REVIEW)
                    }.onFailure { error ->
                        _state.value = OrderUiState.Error(error.message ?: "Error al aceptar pedido")
                    }
                }
            } catch (e: Exception) {
                _state.value = OrderUiState.Error(e.message ?: "Error al aceptar pedido")
            }
        }
    }

    fun startCooking(draftOrderId: String) {
        viewModelScope.launch {
            try {
                startCookingUseCase(draftOrderId).collect { result ->
                    result.onSuccess {
                        loadOrdersByStatus(DraftOrderStatus.CONFIRMED)
                    }.onFailure { error ->
                        _state.value = OrderUiState.Error(error.message ?: "Error al iniciar cocina")
                    }
                }
            } catch (e: Exception) {
                _state.value = OrderUiState.Error(e.message ?: "Error al iniciar cocina")
            }
        }
    }

    fun markAsReady(draftOrderId: String) {
        viewModelScope.launch {
            try {
                completeOrderUseCase(draftOrderId).collect { result ->
                    result.onSuccess {
                        loadOrdersByStatus(DraftOrderStatus.COOKING)
                    }.onFailure { error ->
                        _state.value = OrderUiState.Error(error.message ?: "Error al marcar como listo")
                    }
                }
            } catch (e: Exception) {
                _state.value = OrderUiState.Error(e.message ?: "Error al marcar como listo")
            }
        }
    }

    // Mecanismo temporal de prueba: crea un DraftOrder con un producto del seed data.
    fun createTestOrder() {
        viewModelScope.launch {
            try {
                val testProduct = Product(
                    id = UUID.fromString("10000000-0001-0000-0000-000000000011"),
                    categoryId = UUID.fromString("00000000-0000-0001-0000-000000000001"),
                    name = "Hamburguesa Clásica",
                    description = "Hamburguesa clásica de JuliosBurger",
                    basePrice = 1200.0,
                    imageUrl = null,
                    isAvailable = true,
                    displayOrder = 0
                )
                val params = CreateDraftOrderUseCase.Params(
                    customerPhone = "+521234567890",
                    items = listOf(
                        DraftOrderItem(
                            productSnapshot = testProduct,
                            quantity = 1,
                            modifierSnapshot = emptyList()
                        )
                    )
                )
                createDraftOrderUseCase(params).collect {
                    loadOrders()
                }
            } catch (e: Exception) {
                _state.value = OrderUiState.Error(e.message ?: "Error al crear pedido")
            }
        }
    }
}
