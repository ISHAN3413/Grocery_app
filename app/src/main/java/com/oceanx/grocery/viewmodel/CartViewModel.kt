package com.oceanx.grocery.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.oceanx.grocery.data.local.AppDatabase
import com.oceanx.grocery.data.model.CartItem
import com.oceanx.grocery.data.model.Product
import com.oceanx.grocery.data.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CartRepository

    val cartItems: LiveData<List<CartItem>>

    init {
        val dao = AppDatabase.getInstance(application).cartDao()
        repository = CartRepository(dao)
        cartItems = repository.cartItems
    }

    fun addToCart(product: Product) = viewModelScope.launch {
        repository.addToCart(product)
    }

    fun increaseQty(item: CartItem) = viewModelScope.launch {
        repository.increaseQty(item)
    }

    fun decreaseQty(item: CartItem) = viewModelScope.launch {
        repository.decreaseQty(item)
    }

    fun removeItem(item: CartItem) = viewModelScope.launch {
        repository.removeItem(item)
    }

    fun clearCart() = viewModelScope.launch {
        repository.clearCart()
    }

    val totalPrice: LiveData<Double> = cartItems.map { items ->
        items.sumOf { it.price * it.quantity }
    }

    val cartCount: LiveData<Int> = cartItems.map { items ->
        items.sumOf { it.quantity }
    }
}