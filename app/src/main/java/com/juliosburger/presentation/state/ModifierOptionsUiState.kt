package com.juliosburger.presentation.state

import com.juliosburger.domain.model.ModifierOption

sealed interface ModifierOptionsUiState {
    data object Loading : ModifierOptionsUiState
    data class Success(val modifierOptions: List<ModifierOption>) : ModifierOptionsUiState
    data class Error(val message: String) : ModifierOptionsUiState
}
