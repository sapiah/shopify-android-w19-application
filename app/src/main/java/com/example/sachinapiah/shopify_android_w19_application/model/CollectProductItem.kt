package com.example.sachinapiah.shopify_android_w19_application.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CollectProductItem(
    @SerializedName("id") val collectProductItemId: Long,
    @SerializedName("product_id") val collectProductItemProductId: String
) : Serializable