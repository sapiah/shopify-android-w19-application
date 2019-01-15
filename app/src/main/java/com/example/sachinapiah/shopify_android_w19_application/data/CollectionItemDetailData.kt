package com.example.sachinapiah.shopify_android_w19_application.data

import java.io.Serializable


data class CollectionItemDetailData(
    val productItemTitle: String,
    val collectionItemTitle: String,
    val productItemCount: Int,
    val collectionItemImageUrl: String
) : Serializable
