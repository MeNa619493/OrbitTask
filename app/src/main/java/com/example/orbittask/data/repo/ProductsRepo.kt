package com.example.orbittask.data.repo

import com.example.orbittask.data.models.ProductsResponse

interface ProductsRepo {
    suspend fun getProductsFromRemote(): ProductsResponse
}