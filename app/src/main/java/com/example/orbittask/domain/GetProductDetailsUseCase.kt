package com.example.orbittask.domain

import com.example.orbittask.data.repo.ProductsRepo

class GetProductDetailsUseCase(private val repo: ProductsRepo) {
    suspend operator fun invoke(id: Int) = repo.getProductDetailsFromRemote(id)
}