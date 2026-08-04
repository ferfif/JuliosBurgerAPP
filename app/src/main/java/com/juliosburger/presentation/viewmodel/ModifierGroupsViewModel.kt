package com.juliosburger.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliosburger.domain.usecase.GetModifierGroupsUseCase
import com.juliosburger.presentation.state.ModifierGroupsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifierGroupsViewModel @Inject constructor(
    private val getModifierGroupsUseCase: GetModifierGroupsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ModifierGroupsUiState>(ModifierGroupsUiState.Loading)
    val state: StateFlow<ModifierGroupsUiState> = _state.asStateFlow()

    fun loadModifierGroups(productId: String) {
        viewModelScope.launch {
            _state.value = ModifierGroupsUiState.Loading
            try {
                getModifierGroupsUseCase(productId).collect { result ->
                    _state.value = ModifierGroupsUiState.Success(result)
                }
            } catch (e: Exception) {
                _state.value = ModifierGroupsUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
