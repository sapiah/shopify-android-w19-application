package com.example.sachinapiah.shopify_android_w19_application.networking

import com.example.sachinapiah.shopify_android_w19_application.model.CollectionItem
import com.example.sachinapiah.shopify_android_w19_application.model.CollectsItem
import com.example.sachinapiah.shopify_android_w19_application.model.ProductItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface CollectionRetrofitService {

    @GET("custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    fun getCollectionItems(): Call<CollectionItem>


    @GET("admin/collects.json?collection_id={collection_id}&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    fun getCollects(@Path("collection_id") collection_id: String): List<CollectsItem>

    @GET("products.json?ids={products_id}&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    fun getProductItems(@Path("product_id") product_id: String): ProductItem
}