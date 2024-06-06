package com.example.orbittask.data.network

import com.example.orbittask.data.models.Product
import com.example.orbittask.data.models.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/products")
    suspend fun getProducts(): ProductsResponse

    @GET("/products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): Product
}