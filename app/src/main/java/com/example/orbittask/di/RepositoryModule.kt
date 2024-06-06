package com.example.orbittask.di

import com.example.orbittask.data.network.ApiService
import com.example.orbittask.data.repo.ProductsRepo
import com.example.orbittask.data.repo.ProductsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepo(apiService: ApiService): ProductsRepo {
        return ProductsRepoImpl(apiService)
    }

}