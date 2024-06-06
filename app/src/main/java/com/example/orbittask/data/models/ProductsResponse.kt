package com.example.orbittask.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsResponse(
    val limit: Int? = null,
    val products: List<Product>? = null,
    val skip: Int? = null,
    val total: Int? = null
): Parcelable