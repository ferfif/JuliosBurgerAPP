package com.juliosburger.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juliosburger.domain.model.Product
import com.juliosburger.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    fun loadProducts(categoryId: String) {
        viewModelScope.launch {
            getProductsUseCase(categoryId).collect { _products.value = it }
        }
    }
}
