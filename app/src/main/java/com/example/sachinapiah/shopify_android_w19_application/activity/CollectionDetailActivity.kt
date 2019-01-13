package com.example.sachinapiah.shopify_android_w19_application.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sachinapiah.shopify_android_w19_application.R
import com.example.sachinapiah.shopify_android_w19_application.adapter.CollectionDetailAdapter
import com.example.sachinapiah.shopify_android_w19_application.model.CollectionDetailItem
import kotlinx.android.synthetic.main.collection_details_list.*
import java.util.*

class CollectionDetailActivity : AppCompatActivity() {

    private val items: ArrayList<CollectionDetailItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_details_list)
        addItemsDetails()

        rv_collection_detail.layoutManager = LinearLayoutManager(this)
        rv_collection_detail.adapter = CollectionDetailAdapter(items, this)
        rv_collection_detail.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    private fun addItemsDetails() {
        val imageUrl =
            "https://cdn.shopify.com/s/files/1/1000/7970/collections/Aerodynamic_20Cotton_20Keyboard_grande_b213aa7f-9a10-4860-8618-76d5609f2c19.png?v=1545072718"
        items.add(CollectionDetailItem("Product 1", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 2", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 3", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 4", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 5", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 6", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 7", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 8", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 10", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 11", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 12", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 13", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 14", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 9", "Collection 1", 5, imageUrl))
        items.add(CollectionDetailItem("Product 15", "Collection 1", 5, imageUrl))
    }
}