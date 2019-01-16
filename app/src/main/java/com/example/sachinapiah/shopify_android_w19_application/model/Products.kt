package com.example.sachinapiah.shopify_android_w19_application.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Products(
    @SerializedName("products") val products: List<ProductItem>
) : Serializable