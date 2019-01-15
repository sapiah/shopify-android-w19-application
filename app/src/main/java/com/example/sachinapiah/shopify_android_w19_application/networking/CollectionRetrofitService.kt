package com.example.sachinapiah.shopify_android_w19_application.networking

import com.example.sachinapiah.shopify_android_w19_application.model.CollectionItem
import com.example.sachinapiah.shopify_android_w19_application.model.Collects
import com.example.sachinapiah.shopify_android_w19_application.model.ProductItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CollectionRetrofitService {

    @GET("custom_collections.json")
    fun getCollectionItems(@Query("page") page: String, @Query("access_token") accessToken: String): Call<CollectionItem>

    @GET("collects.json")
    fun getCollects(@Query("collection_id") collectionId: String, @Query("page") page: String, @Query("access_token") accessToken: String): Call<Collects>

    @GET("products.json")
    fun getProductItems(@Query("products_id") productsId: String, @Query("page") page: String, @Query("access_token") accessToken: String): ProductItem
}