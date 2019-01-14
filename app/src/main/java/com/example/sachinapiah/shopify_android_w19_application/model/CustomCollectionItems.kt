package com.example.sachinapiah.shopify_android_w19_application.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CustomCollectionItems(
    @SerializedName("id") val collectionItemId: Long,
    @SerializedName("title") val collectionItemTitle: String,
    @SerializedName("image") val imageDetail: ImageDetail
) : Serializable