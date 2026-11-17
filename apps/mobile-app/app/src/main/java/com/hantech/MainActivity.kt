package com.hantech

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.hantech.network.FeedbackRequest
import com.hantech.network.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etService = findViewById<TextInputEditText>(R.id.etService)
        val etRating = findViewById<TextInputEditText>(R.id.etRating)
        val etComments = findViewById<TextInputEditText>(R.id.etComments)
        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmit)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val service = etService.text.toString().trim()
            val ratingStr = etRating.text.toString().trim()
            val comments = etComments.text.toString().trim()

            if (name.isEmpty() || service.isEmpty() || ratingStr.isEmpty()) {
                tvStatus.text = "Please fill all required fields."
                tvStatus.setTextColor(Color.RED)
                return@setOnClickListener
            }

            val rating = ratingStr.toIntOrNull()
            if (rating == null || rating < 1 || rating > 5) {
                tvStatus.text = "Rating must be a number between 1 and 5."
                tvStatus.setTextColor(Color.RED)
                return@setOnClickListener
            }

            progressBar.visibility = View.VISIBLE
            tvStatus.text = ""
            btnSubmit.isEnabled = false

            lifecycleScope.launch {
                try {
                    val request = FeedbackRequest(name, service, rating, comments)
                    val response = RetrofitClient.apiService.submitFeedback(request)

                    if (response.isSuccessful) {
                        tvStatus.text = "Feedback Submitted Successfully!"
                        tvStatus.setTextColor(Color.parseColor("#2E7D32"))
                        etName.text?.clear()
                        etService.text?.clear()
                        etRating.text?.clear()
                        etComments.text?.clear()
                    } else {
                        tvStatus.text = "Server Error: ${response.code()}"
                        tvStatus.setTextColor(Color.RED)
                    }
                } catch (e: Exception) {
                    tvStatus.text = "Network Error. Check backend URL."
                    tvStatus.setTextColor(Color.RED)
                } finally {
                    progressBar.visibility = View.GONE
                    btnSubmit.isEnabled = true
                }
            }
        }
    }
}
