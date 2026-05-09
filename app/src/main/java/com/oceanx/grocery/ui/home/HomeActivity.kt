package com.oceanx.grocery.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.oceanx.grocery.databinding.ActivityHomeBinding
import com.oceanx.grocery.ui.cart.CartActivity
import com.oceanx.grocery.utils.SampleData
import com.oceanx.grocery.viewmodel.CartViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: CartViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSearch()
        observeCartCount()

        binding.btnCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = ProductAdapter { product ->
            viewModel.addToCart(product)
            Snackbar.make(binding.root, "${product.emoji} ${product.name} added!", Snackbar.LENGTH_SHORT).show()
        }
        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
        adapter.submitList(SampleData.getProducts())
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener { text ->
            adapter.filter(text.toString())
        }
    }

    private fun observeCartCount() {
        viewModel.cartCount.observe(this) { count ->
            binding.btnCart.text = if (count > 0) "🛒 Cart ($count)" else "🛒 Cart"
        }
    }
}