package com.example.orbittask.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val brand: String? = null,
    val category: String? = null,
    val description: String? = null,
    val discountPercentage: Double? = null,
    val id: Int? = null,
    val images: List<String>? = null,
    val price: Double? = null,
    val rating: Double? = null,
    val stock: Int? = null,
    val thumbnail: String? = null,
    val title: String? = null
): Parcelable