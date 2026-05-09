package com.oceanx.grocery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oceanx.grocery.data.model.Product
import com.oceanx.grocery.databinding.ItemProductBinding

class ProductAdapter(
    private val onAddToCart: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var allProducts: List<Product> = emptyList()
    private var filteredProducts: List<Product> = emptyList()

    fun submitList(list: List<Product>) {
        allProducts = list
        filteredProducts = list
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredProducts = if (query.isEmpty()) {
            allProducts
        } else {
            allProducts.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.category.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(filteredProducts[position])
    }

    override fun getItemCount() = filteredProducts.size

    inner class ProductViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.tvEmoji.text    = product.emoji
            binding.tvName.text     = product.name
            binding.tvCategory.text = product.category
            binding.tvPrice.text    = "₹${product.price}"
            binding.btnAdd.setOnClickListener { onAddToCart(product) }
        }
    }
}