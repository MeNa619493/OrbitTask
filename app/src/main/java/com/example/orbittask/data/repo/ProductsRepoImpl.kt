package com.example.orbittask.data.repo

import com.example.orbittask.data.network.ApiService

class ProductsRepoImpl(private val apiService: ApiService) : ProductsRepo {
    override suspend fun getProductsFromRemote() = apiService.getProducts()

    override suspend fun getProductDetailsFromRemote(id: Int) = apiService.getProductDetails(id)
}