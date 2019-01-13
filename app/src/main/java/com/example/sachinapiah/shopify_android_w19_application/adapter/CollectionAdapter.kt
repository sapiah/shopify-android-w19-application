package com.example.sachinapiah.shopify_android_w19_application.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sachinapiah.shopify_android_w19_application.R
import com.example.sachinapiah.shopify_android_w19_application.activity.CollectionDetailActivity
import kotlinx.android.synthetic.main.collection_list_recycler_view_item.view.*
import java.util.*


class CollectionAdapter(private val items: ArrayList<String>, context: Context) :
    RecyclerView.Adapter<CollectionViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val view = inflater.inflate(R.layout.collection_list_recycler_view_item, parent, false)
        return CollectionViewHolderValid(view)
    }
}

abstract class CollectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(item: String)
}

class CollectionViewHolderValid(view: View) : CollectionViewHolder(view) {
    private val context = itemView.context
    private val tvCollectionTitle: TextView = view.tv_collection_title

    init {
        itemView.setOnClickListener {
            Log.d("RecyclerView", "Collection item CLICK!")
            goToCollectionDetailActivity()
        }
    }

    override fun onBind(item: String) {
        tvCollectionTitle.text = item
    }

    private fun goToCollectionDetailActivity() {
        context?.startActivity(Intent(context, CollectionDetailActivity::class.java))
    }
}

