package com.hantech

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.hantech.network.FeedbackRequest
import com.hantech.network.RetrofitClient
import kotlinx.coroutines.launch

class FeedbackFragment : Fragment(R.layout.fragment_dashboard) { // Reusing the dashboard layout we made earlier
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etName = view.findViewById<TextInputEditText>(R.id.etName)
        val etService = view.findViewById<TextInputEditText>(R.id.etService)
        val etRating = view.findViewById<TextInputEditText>(R.id.etRating)
        val etComments = view.findViewById<TextInputEditText>(R.id.etComments)
        val btnSubmit = view.findViewById<MaterialButton>(R.id.btnSubmit)
        val tvStatus = view.findViewById<TextView>(R.id.tvStatus)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

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
                tvStatus.text = "Rating must be 1-5."
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
                        tvStatus.text = "Feedback Submitted!"
                        tvStatus.setTextColor(Color.parseColor("#2E7D32"))
                        etName.text?.clear(); etService.text?.clear(); etRating.text?.clear(); etComments.text?.clear()
                    } else {
                        tvStatus.text = "Server Error: ${response.code()}"
                        tvStatus.setTextColor(Color.RED)
                    }
                } catch (e: Exception) {
                    tvStatus.text = "Network Error. Check backend."
                    tvStatus.setTextColor(Color.RED)
                } finally {
                    progressBar.visibility = View.GONE
                    btnSubmit.isEnabled = true
                }
            }
        }
    }
}
