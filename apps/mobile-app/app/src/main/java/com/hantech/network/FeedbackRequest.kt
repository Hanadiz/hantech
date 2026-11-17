package com.hantech.network

data class FeedbackRequest(
    val customer_name: String,
    val service_used: String,
    val rating: Int,
    val comments: String
)
