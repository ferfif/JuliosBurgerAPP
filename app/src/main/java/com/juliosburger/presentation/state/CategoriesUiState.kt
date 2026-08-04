package com.juliosburger.presentation.state

import com.juliosburger.domain.model.Category

sealed interface CategoriesUiState {
    data object Loading : CategoriesUiState
    data class Success(val categories: List<Category>) : CategoriesUiState
    data class Error(val message: String) : CategoriesUiState
}
