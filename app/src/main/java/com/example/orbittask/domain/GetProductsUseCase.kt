package com.example.orbittask.domain

import com.example.orbittask.data.repo.ProductsRepo

class GetProductsUseCase(private val repo: ProductsRepo) {
    suspend operator fun invoke() = repo.getProductsFromRemote()
}