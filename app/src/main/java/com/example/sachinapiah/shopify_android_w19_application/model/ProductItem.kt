package com.example.sachinapiah.shopify_android_w19_application.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductItem(
    @SerializedName("id") val productItemId: Long,
    @SerializedName("title") val productItemTitle: String,
    @SerializedName("variants") val productItemVariants: List<ProductVariant>
) : Serializable