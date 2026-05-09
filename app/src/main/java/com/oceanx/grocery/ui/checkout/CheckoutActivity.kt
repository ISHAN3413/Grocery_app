package com.oceanx.grocery.ui.checkout

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.oceanx.grocery.databinding.ActivityCheckoutBinding
import com.oceanx.grocery.ui.ordersuccess.OrderSuccessActivity
import com.oceanx.grocery.viewmodel.CartViewModel

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    private val viewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        observeTotal()

        binding.btnPlaceOrder.setOnClickListener {
            validateAndPlaceOrder()
        }
    }

    private fun observeTotal() {
        viewModel.totalPrice.observe(this) { total ->
            val formatted = "₹${"%.2f".format(total)}"
            binding.tvOrderTotal.text = formatted
            binding.tvToPay.text      = formatted
        }
    }

    private fun validateAndPlaceOrder() {
        val address = binding.etAddress.text.toString().trim()
        val pincode = binding.etPincode.text.toString().trim()

        when {
            address.isEmpty() -> {
                binding.tilAddress.error = "Please enter delivery address"
                return
            }
            pincode.length != 6 -> {
                binding.tilAddress.error = null
                binding.etPincode.error  = "Enter valid 6-digit pincode"
                return
            }
            else -> {
                binding.tilAddress.error = null
                binding.etPincode.error  = null
                placeOrder(address)
            }
        }
    }

    private fun placeOrder(address: String) {
        val orderId      = "OX${System.currentTimeMillis() % 100000}"
        val total        = viewModel.totalPrice.value ?: 0.0
        val paymentMode  = if (binding.rbCod.isChecked) "Cash on Delivery" else "Online (UPI)"

        viewModel.clearCart()

        val intent = Intent(this, OrderSuccessActivity::class.java).apply {
            putExtra("ORDER_ID",     orderId)
            putExtra("TOTAL",        total)
            putExtra("ADDRESS",      address)
            putExtra("PAYMENT_MODE", paymentMode)
        }
        startActivity(intent)
        finishAffinity() // clears entire back stack
    }
}