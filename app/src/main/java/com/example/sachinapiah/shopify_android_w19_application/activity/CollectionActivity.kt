package com.example.sachinapiah.shopify_android_w19_application.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sachinapiah.shopify_android_w19_application.R
import com.example.sachinapiah.shopify_android_w19_application.adapter.CollectionAdapter
import kotlinx.android.synthetic.main.collection_list.*
import java.util.*

class CollectionActivity : AppCompatActivity() {

    private val items: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_list)

        addItems()

        rv_collections.layoutManager = LinearLayoutManager(this)
        rv_collections.adapter = CollectionAdapter(items, this)
        rv_collections.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    fun addItems() {
        items.add("collection 1")
        items.add("collection 2")
        items.add("collection 3")
        items.add("collection 4")
        items.add("collection 5")
        items.add("collection 6")
        items.add("collection 7")
        items.add("collection 8")
        items.add("collection 9")
        items.add("collection 10")
        items.add("collection 12")
        items.add("collection 13")
        items.add("collection 14")
        items.add("collection 15")
    }

}
