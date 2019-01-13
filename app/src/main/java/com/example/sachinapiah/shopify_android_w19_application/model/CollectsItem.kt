package com.example.sachinapiah.shopify_android_w19_application.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CollectsItem(
    @SerializedName("id") val collectsItemId: Long,
    @SerializedName("product_id") val collectsItemProductId: String
): Serializable