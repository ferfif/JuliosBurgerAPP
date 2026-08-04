package com.juliosburger.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliosburger.domain.usecase.GetModifierOptionsUseCase
import com.juliosburger.presentation.state.ModifierOptionsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifierOptionsViewModel @Inject constructor(
    private val getModifierOptionsUseCase: GetModifierOptionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ModifierOptionsUiState>(ModifierOptionsUiState.Loading)
    val state: StateFlow<ModifierOptionsUiState> = _state.asStateFlow()

    fun loadModifierOptions(groupId: String) {
        viewModelScope.launch {
            _state.value = ModifierOptionsUiState.Loading
            try {
                getModifierOptionsUseCase(groupId).collect { result ->
                    _state.value = ModifierOptionsUiState.Success(result)
                }
            } catch (e: Exception) {
                _state.value = ModifierOptionsUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
