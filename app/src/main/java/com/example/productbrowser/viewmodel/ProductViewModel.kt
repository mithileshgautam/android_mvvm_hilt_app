package com.example.productbrowser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productbrowser.model.Product
import com.example.productbrowser.repository.ProductRepository
import com.example.productbrowser.uistate.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel(){

    private val _productState = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())
    val productState: StateFlow<Resource<List<Product>>> = _productState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            repository.getProducts().collect {
                _productState.value = it
            }
        }
    }
}