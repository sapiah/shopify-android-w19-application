package com.example.sachinapiah.shopify_android_w19_application.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageDetail(
    @SerializedName("src") val imageUrl: String
) : Serializable