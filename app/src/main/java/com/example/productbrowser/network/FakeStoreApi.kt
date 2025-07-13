package com.example.productbrowser.network

import com.example.productbrowser.model.Product
import retrofit2.http.GET

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<Product>

    companion object {
        const val BASE_URL = "https://fakestoreapi.com/"
    }
}