package com.oceanx.grocery.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oceanx.grocery.data.model.CartItem
import com.oceanx.grocery.databinding.ItemCartBinding

class CartAdapter(
    private val onIncrease: (CartItem) -> Unit,
    private val onDecrease: (CartItem) -> Unit,
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var items: List<CartItem> = emptyList()

    fun submitList(list: List<CartItem>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class CartViewHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem) {
            binding.tvEmoji.text = item.emoji
            binding.tvName.text  = item.name
            binding.tvPrice.text = "₹${"%.2f".format(item.price * item.quantity)}"
            binding.tvQty.text   = item.quantity.toString()
            binding.btnPlus.setOnClickListener  { onIncrease(item) }
            binding.btnMinus.setOnClickListener { onDecrease(item) }
        }
    }
}