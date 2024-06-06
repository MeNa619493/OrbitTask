package com.example.orbittask.view.products.view_model

import android.os.Parcelable
import com.example.orbittask.data.models.Product
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductListState(
    val loading: Boolean = false,
    val data: List<Product>? = emptyList(),
    val error: String? = null
) : Parcelable