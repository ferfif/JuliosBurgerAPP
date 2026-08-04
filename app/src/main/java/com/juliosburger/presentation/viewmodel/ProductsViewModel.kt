package com.juliosburger.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliosburger.domain.usecase.GetProductsUseCase
import com.juliosburger.presentation.state.ProductsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ProductsUiState>(ProductsUiState.Loading)
    val state: StateFlow<ProductsUiState> = _state.asStateFlow()

    fun loadProducts(categoryId: String) {
        viewModelScope.launch {
            _state.value = ProductsUiState.Loading
            try {
                getProductsUseCase(categoryId).collect { result ->
                    _state.value = ProductsUiState.Success(result)
                }
            } catch (e: Exception) {
                _state.value = ProductsUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
