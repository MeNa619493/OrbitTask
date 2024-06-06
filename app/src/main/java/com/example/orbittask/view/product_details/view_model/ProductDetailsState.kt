package com.example.orbittask.view.product_details.view_model

import android.os.Parcelable
import com.example.orbittask.data.models.Product
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetailsState(
    val loading: Boolean = false,
    val data: Product? = Product(),
    val error: String? = null
) : Parcelable