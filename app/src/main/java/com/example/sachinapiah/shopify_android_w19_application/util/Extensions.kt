package com.example.sachinapiah.shopify_android_w19_application.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sachinapiah.shopify_android_w19_application.R

/**
 * Purpose: Helper method to load images using Glide onto an ImageView
 */
fun ImageView.insertCollectionImage(collectionImageUrl: String) {
    Glide.with(context)
        .load(collectionImageUrl)
        .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}