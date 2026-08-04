package com.juliosburger.presentation.state

import com.juliosburger.domain.model.Product

sealed interface ProductsUiState {
    data object Loading : ProductsUiState
    data class Success(val products: List<Product>) : ProductsUiState
    data class Error(val message: String) : ProductsUiState
}
