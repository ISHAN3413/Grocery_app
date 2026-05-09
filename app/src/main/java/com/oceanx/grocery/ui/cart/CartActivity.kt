package com.oceanx.grocery.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oceanx.grocery.databinding.ActivityCartBinding
import com.oceanx.grocery.ui.checkout.CheckoutActivity
import com.oceanx.grocery.viewmodel.CartViewModel

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private val viewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        setupRecyclerView()
        observeCart()

        binding.btnCheckout.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter(
            onIncrease = { viewModel.increaseQty(it) },
            onDecrease = { viewModel.decreaseQty(it) }
        )
        binding.rvCart.layoutManager = LinearLayoutManager(this)
        binding.rvCart.adapter = adapter
    }

    private fun observeCart() {
        viewModel.cartItems.observe(this) { items ->
            adapter.submitList(items)
            binding.layoutEmpty.visibility = if (items.isEmpty()) View.VISIBLE else View.GONE
            binding.rvCart.visibility      = if (items.isEmpty()) View.GONE  else View.VISIBLE
            binding.btnCheckout.isEnabled  = items.isNotEmpty()
        }

        viewModel.totalPrice.observe(this) { total ->
            binding.tvTotal.text = "₹${"%.2f".format(total)}"
        }
    }
}