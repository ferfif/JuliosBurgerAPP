package com.juliosburger.presentation.state

import com.juliosburger.domain.model.DraftOrder

sealed interface OrderUiState {
    data object Loading : OrderUiState
    data class Success(val orders: List<DraftOrder>) : OrderUiState
    data class Error(val message: String) : OrderUiState
}
