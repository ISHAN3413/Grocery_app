package com.oceanx.grocery.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.oceanx.grocery.databinding.ActivityLoginBinding
import com.oceanx.grocery.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetOtp.setOnClickListener {
            val phone = binding.etPhone.text.toString().trim()
            when {
                phone.isEmpty() -> {
                    binding.tilPhone.error = "Please enter your mobile number"
                }
                phone.length != 10 -> {
                    binding.tilPhone.error = "Enter a valid 10-digit number"
                }
                else -> {
                    binding.tilPhone.error = null
                    binding.layoutOtp.visibility = View.VISIBLE
                    Snackbar.make(binding.root, "OTP sent! Use 1234", Snackbar.LENGTH_LONG).show()
                }
            }
        }

        binding.btnVerify.setOnClickListener {
            val otp = binding.etOtp.text.toString().trim()
            when {
                otp.isEmpty() -> {
                    binding.tilOtp.error = "Please enter the OTP"
                }
                otp != "1234" -> {
                    binding.tilOtp.error = "Invalid OTP. Hint: 1234"
                }
                else -> {
                    binding.tilOtp.error = null
                    navigateToHome()
                }
            }
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish() // removes Login from back stack so user can't go back
    }
}