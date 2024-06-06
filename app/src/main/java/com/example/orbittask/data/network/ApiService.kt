package com.example.orbittask.data.network

import com.example.orbittask.data.models.ProductsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    suspend fun getProducts(): ProductsResponse
}