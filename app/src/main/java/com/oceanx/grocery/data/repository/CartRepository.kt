package com.oceanx.grocery.data.repository

import androidx.lifecycle.LiveData
import com.oceanx.grocery.data.local.CartDao
import com.oceanx.grocery.data.model.CartItem
import com.oceanx.grocery.data.model.Product

class CartRepository(private val dao: CartDao) {

    val cartItems: LiveData<List<CartItem>> = dao.getAllItems()

    suspend fun addToCart(product: Product) {
        val existing = dao.getItemById(product.id)
        if (existing != null) {
            dao.insertOrUpdate(existing.copy(quantity = existing.quantity + 1))
        } else {
            dao.insertOrUpdate(
                CartItem(
                    productId = product.id,
                    name = product.name,
                    price = product.price,
                    emoji = product.emoji,
                    quantity = 1
                )
            )
        }
    }

    suspend fun increaseQty(item: CartItem) =
        dao.insertOrUpdate(item.copy(quantity = item.quantity + 1))

    suspend fun decreaseQty(item: CartItem) {
        if (item.quantity > 1) dao.insertOrUpdate(item.copy(quantity = item.quantity - 1))
        else dao.delete(item)
    }

    suspend fun removeItem(item: CartItem) = dao.delete(item)

    suspend fun clearCart() = dao.clearCart()
}