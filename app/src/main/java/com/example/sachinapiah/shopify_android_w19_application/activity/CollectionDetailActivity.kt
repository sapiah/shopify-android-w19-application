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
import com.example.sachinapiah.shopify_android_w19_application.model.Products
import com.example.sachinapiah.shopify_android_w19_application.networking.CallsRetrofitService
import com.example.sachinapiah.shopify_android_w19_application.networking.RetrofitService
import com.example.sachinapiah.shopify_android_w19_application.util.CollectionDetailActivityUtil
import kotlinx.android.synthetic.main.collection_details_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectionDetailActivity : AppCompatActivity() {

    private val itemDetailData: ArrayList<CollectionItemDetailData> = ArrayList()
    private lateinit var collectionItemData: CollectionItemData
    private var callsRetrofitService: CallsRetrofitService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_details_list)
        collectionItemData = intent.getSerializableExtra("CollectionItem") as CollectionItemData
        callsRetrofitService = RetrofitService().getClient()?.create(CallsRetrofitService::class.java)
        getCollectsItem()
    }

    /***
     * Purpose: Setup and create a retrofit call to retrieve the collects item information
     *
     */
    private fun getCollectsItem() {
        val collectsItemsResponse =
            callsRetrofitService?.getCollects(
                collectionItemData.collectionItemId.toString(),
                Constants.PARAM_PAGE,
                Constants.PARAM_ACCESS_TOKEN
            )
        collectsItemsResponse?.enqueue(object : Callback<Collects> {

            override fun onResponse(call: Call<Collects>, response: Response<Collects>) {
                lateinit var productIdsUrlSnippet: String
                val collectsResponseBody = response.body()
                productIdsUrlSnippet = CollectionDetailActivityUtil.getProductItemIds(collectsResponseBody)
                getProductItemDetails(productIdsUrlSnippet)
            }

            override fun onFailure(call: Call<Collects>, t: Throwable) {
                System.out.println(t.localizedMessage)
            }
        })
    }

    /***
     * Purpose: Setup and create a retrofit call to retrieve the product item information
     *
     */
    fun getProductItemDetails(productIdsUrlSnippet: String) {
        val productItemsResponse =
            callsRetrofitService?.getProductItems(
                productIdsUrlSnippet,
                Constants.PARAM_PAGE,
                Constants.PARAM_ACCESS_TOKEN
            )
        productItemsResponse?.enqueue(object : Callback<Products> {

            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                val context = this@CollectionDetailActivity
                val productItemsResponseBody = response.body()
                populateCollectionItemDetailData(productItemsResponseBody)

                loadRecyclerView(context)
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                System.out.println(t.localizedMessage)
            }
        })

    }

    /***
     * Purpose: Opening the response body and further iterating through the list of products and populating the
     * CollectionItemDetailData data object
     *
     */
    fun populateCollectionItemDetailData(productItemsResponseBody: Products?) {
        productItemsResponseBody?.let { it ->
            it.products.forEach { productItem ->

                itemDetailData.add(
                    CollectionItemDetailData(
                        productItem.productItemTitle,
                        "Collection - " + collectionItemData.collectionItemTitle,
                        "Qty - " + CollectionDetailActivityUtil.getProductVariantQty(productItem.productItemVariants).toString(),
                        collectionItemData.collectionItemImageUrl
                    )

                )
            }
        }
    }


    fun loadRecyclerView(context: Context) {
        rv_collection_detail.layoutManager = LinearLayoutManager(context)
        rv_collection_detail.adapter = CollectionDetailAdapter(itemDetailData, context)
        rv_collection_detail.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }
}