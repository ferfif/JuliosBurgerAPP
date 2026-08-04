package com.juliosburger.presentation.state

import com.juliosburger.domain.model.ModifierGroup

sealed interface ModifierGroupsUiState {
    data object Loading : ModifierGroupsUiState
    data class Success(val modifierGroups: List<ModifierGroup>) : ModifierGroupsUiState
    data class Error(val message: String) : ModifierGroupsUiState
}
