package com.hantech.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("feedback/")
    suspend fun submitFeedback(@Body request: FeedbackRequest): Response<Unit>
}

object RetrofitClient {
    // TODO: PASTE YOUR RENDER.COM URL HERE LATER
    private const val BASE_URL = "https://your-render-url.onrender.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
