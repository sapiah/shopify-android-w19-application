package com.example.sachinapiah.shopify_android_w19_application.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sachinapiah.shopify_android_w19_application.Constants
import com.example.sachinapiah.shopify_android_w19_application.R
import com.example.sachinapiah.shopify_android_w19_application.adapter.CollectionDetailAdapter
import com.example.sachinapiah.shopify_android_w19_application.data.CollectionItemData
import com.example.sachinapiah.shopify_android_w19_application.data.CollectionItemDetailData
import com.example.sachinapiah.shopify_android_w19_application.model.Collects
import com.example.sachinapiah.shopify_android_w19_application.networking.CollectionRetrofitService
import com.example.sachinapiah.shopify_android_w19_application.networking.RetrofitService
import kotlinx.android.synthetic.main.collection_details_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectionDetailActivity : AppCompatActivity() {

    private val itemDetailData: ArrayList<CollectionItemDetailData> = ArrayList()
    private lateinit var collectionItemData: CollectionItemData
    //    private val collectionItemDetailData: CollectionItemDetailData? = null
    private var collectsRetrofitService: CollectionRetrofitService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_details_list)
        collectionItemData = intent.getSerializableExtra("CollectionItem") as CollectionItemData

        collectsRetrofitService = RetrofitService().getClient()?.create(CollectionRetrofitService::class.java)

        val collectsItemsResponse =
            collectsRetrofitService?.getCollects(
                collectionItemData.collectionItemId.toString(),
                Constants.PARAM_PAGE,
                Constants.PARAM_ACCESS_TOKEN
            )
        collectsItemsResponse?.enqueue(object : Callback<Collects> {

            override fun onResponse(call: Call<Collects>, response: Response<Collects>) {
                val context = this@CollectionDetailActivity
                val collectsResponseBody = response.body()

                addItemsDetails()
                loadRecyclerView(context)
            }

            override fun onFailure(call: Call<Collects>, t: Throwable) {
                System.out.println(t.localizedMessage)
            }
        })


    }

    fun loadRecyclerView(context: Context) {
        rv_collection_detail.layoutManager = LinearLayoutManager(context)
        rv_collection_detail.adapter = CollectionDetailAdapter(itemDetailData, context)
        rv_collection_detail.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    private fun addItemsDetails() {
        itemDetailData.add(
            CollectionItemDetailData(
                "Product Title",
                collectionItemData.collectionItemTitle,
                5,
                collectionItemData.collectionItemImageUrl
            )
        )
    }
}