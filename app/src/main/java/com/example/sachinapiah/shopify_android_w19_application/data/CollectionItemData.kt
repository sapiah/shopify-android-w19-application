package com.example.sachinapiah.shopify_android_w19_application.data

import java.io.Serializable

data class CollectionItemData(
    val collectionItemId: Long,
    val collectionItemTitle: String,
    val collectionItemImageUrl: String
) : Serializable