package com.example.sachinapiah.shopify_android_w19_application.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sachinapiah.shopify_android_w19_application.R
import com.example.sachinapiah.shopify_android_w19_application.adapter.CollectionDetailAdapter
import com.example.sachinapiah.shopify_android_w19_application.data.CollectionItemData
import com.example.sachinapiah.shopify_android_w19_application.data.CollectionItemDetailData
import kotlinx.android.synthetic.main.collection_details_list.*

class CollectionDetailActivity : AppCompatActivity() {

    private val itemDetailData: ArrayList<CollectionItemDetailData> = ArrayList()
    private lateinit var collectionItemData: CollectionItemData
//    private val collectionItemDetailData: CollectionItemDetailData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_details_list)
        collectionItemData = intent.getSerializableExtra("CollectionItem") as CollectionItemData
        addItemsDetails()

        rv_collection_detail.layoutManager = LinearLayoutManager(this)
        rv_collection_detail.adapter = CollectionDetailAdapter(itemDetailData, this)
        rv_collection_detail.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
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