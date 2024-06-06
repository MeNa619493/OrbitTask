package com.example.orbittask.di

import com.example.orbittask.data.repo.ProductsRepo
import com.example.orbittask.domain.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideRepo(repo: ProductsRepo): GetProductsUseCase {
        return GetProductsUseCase(repo)
    }
}