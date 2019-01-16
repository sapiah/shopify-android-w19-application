package com.example.sachinapiah.shopify_android_w19_application.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductVariant(
    @SerializedName("id") val ProductVariantsId: Long,
    @SerializedName("inventory_quantity") val ProductVariantsInventoryQuantity: Int
) : Serializable