package com.example.sachinapiah.shopify_android_w19_application.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Collects (
    @SerializedName("collects") val collects: List<CollectProductItem>
) : Serializable