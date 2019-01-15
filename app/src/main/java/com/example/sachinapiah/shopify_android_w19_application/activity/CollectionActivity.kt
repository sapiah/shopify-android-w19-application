package com.example.sachinapiah.shopify_android_w19_application.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sachinapiah.shopify_android_w19_application.R
import com.example.sachinapiah.shopify_android_w19_application.adapter.CollectionAdapter
import com.example.sachinapiah.shopify_android_w19_application.data.CollectionItemData
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
    private val collectionItems: ArrayList<CollectionItemData> = ArrayList()
    private var collectionRetrofitService: CollectionRetrofitService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_list)


        collectionRetrofitService = RetrofitService().getClient()?.create(CollectionRetrofitService::class.java)

        val collectionItemsResponse = collectionRetrofitService?.getCollectionItems()
        collectionItemsResponse?.enqueue(object : Callback<CollectionItem> {

            override fun onResponse(call: Call<CollectionItem>, response: Response<CollectionItem>) {
                val context = this@CollectionActivity
                val collectionItemResponseBody = response.body()
                addCollectionItems(collectionItemResponseBody)
                loadRecyclerView(context)
            }

            override fun onFailure(call: Call<CollectionItem>, t: Throwable) {
                System.out.println(t.localizedMessage)
            }
        })

    }

    fun addCollectionItems(collectionItemResponseBody: CollectionItem?) {
        collectionItemResponseBody?.customCollectionItems?.forEach {
            collectionItems.add(
                CollectionItemData(
                    it.collectionItemId,
                    it.collectionItemTitle,
                    it.imageDetail.imageUrl
                )
            )
            items.add(it.collectionItemTitle)
        }
    }

    fun loadRecyclerView(context: Context) {
        rv_collections.layoutManager = LinearLayoutManager(context)
        rv_collections.adapter = CollectionAdapter(collectionItems, context)
        rv_collections.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

}
