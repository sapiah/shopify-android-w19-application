package com.example.sachinapiah.shopify_android_w19_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sachinapiah.shopify_android_w19_application.R
import com.example.sachinapiah.shopify_android_w19_application.data.CollectionItemDetailData
import com.example.sachinapiah.shopify_android_w19_application.util.insertCollectionImage
import kotlinx.android.synthetic.main.collection_details_recycler_view_item.view.*
import java.util.*


class CollectionDetailAdapter(private val itemDetailData: ArrayList<CollectionItemDetailData>, context: Context) :
    RecyclerView.Adapter<CollectionDetailViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun getItemCount(): Int {
        return itemDetailData.size
    }

    override fun onBindViewHolder(holder: CollectionDetailViewHolder, position: Int) {
        holder.onBind(itemDetailData[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionDetailViewHolder {
        val view = inflater.inflate(R.layout.collection_details_recycler_view_item, parent, false)
        return CollectionDetailViewHolderValid(view)
    }
}

abstract class CollectionDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(itemDetailData: CollectionItemDetailData)

}

class CollectionDetailViewHolderValid(view: View) : CollectionDetailViewHolder(view) {

    private val tvProductTitle: TextView = view.tv_product_item_title
    private val tvCollectionTitle: TextView = view.tv_collection_item_title
    private val tvProductCount: TextView = view.tv_product_item_variants_count
    private val ivCollectionImage: ImageView = view.iv_collection_item_detail

    override fun onBind(itemDetailData: CollectionItemDetailData) {
        tvProductTitle.text = itemDetailData.productItemTitle
        tvCollectionTitle.text = itemDetailData.collectionItemTitle
        tvProductCount.text = itemDetailData.productItemCount
        ivCollectionImage.insertCollectionImage(itemDetailData.collectionItemImageUrl)
    }
}

