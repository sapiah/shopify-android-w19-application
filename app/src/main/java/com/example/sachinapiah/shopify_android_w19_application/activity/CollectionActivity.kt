package com.example.sachinapiah.shopify_android_w19_application.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sachinapiah.shopify_android_w19_application.R
import com.example.sachinapiah.shopify_android_w19_application.adapter.CollectionAdapter
import com.example.sachinapiah.shopify_android_w19_application.model.CollectionItem
import com.example.sachinapiah.shopify_android_w19_application.networking.CollectionRetrofitService
import com.example.sachinapiah.shopify_android_w19_application.networking.RetrofitService
import kotlinx.android.synthetic.main.collection_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CollectionActivity : AppCompatActivity() {

    private val items: ArrayList<String> = ArrayList()
    var collectionRetrofitService: CollectionRetrofitService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_list)


        collectionRetrofitService = RetrofitService().getClient()?.create(CollectionRetrofitService::class.java)

        val collectionItems = collectionRetrofitService?.getCollectionItems()
        collectionItems?.enqueue(object : Callback<CollectionItem> {

            override fun onResponse(call: Call<CollectionItem>, response: Response<CollectionItem>) {
                val collectionItemResponse = response.body()
                System.out.println(collectionItemResponse.toString())
                addCollectionItems(collectionItemResponse)
            }

            override fun onFailure(call: Call<CollectionItem>, t: Throwable) {
                System.out.println(t.localizedMessage)
                //Handle failure
            }
        })

        rv_collections.layoutManager = LinearLayoutManager(this)
        rv_collections.adapter = CollectionAdapter(items, this)
        rv_collections.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    fun addCollectionItems(collectionItem: CollectionItem?) {
//        collectionItem
//        collectionItem?.let {
//
//            it.forEach { collectionItem ->
//                items.add(collectionItem.collectionItemTitle)
//            }
//        }
    }

}
