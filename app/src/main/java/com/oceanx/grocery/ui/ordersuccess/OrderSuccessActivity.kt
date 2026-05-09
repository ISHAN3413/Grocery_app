package com.oceanx.grocery.ui.ordersuccess

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oceanx.grocery.databinding.ActivityOrderSuccessBinding
import com.oceanx.grocery.ui.home.HomeActivity

class OrderSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive data from CheckoutActivity
        val orderId     = intent.getStringExtra("ORDER_ID")     ?: "N/A"
        val total       = intent.getDoubleExtra("TOTAL", 0.0)
        val address     = intent.getStringExtra("ADDRESS")      ?: "N/A"
        val paymentMode = intent.getStringExtra("PAYMENT_MODE") ?: "N/A"

        binding.tvOrderId.text     = "#$orderId"
        binding.tvTotal.text       = "₹${"%.2f".format(total)}"
        binding.tvAddress.text     = address
        binding.tvPaymentMode.text = paymentMode

        // Back to home, clear back stack
        binding.btnContinue.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }
}