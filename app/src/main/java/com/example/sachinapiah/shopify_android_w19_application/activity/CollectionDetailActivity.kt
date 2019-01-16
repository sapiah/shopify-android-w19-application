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
import com.example.sachinapiah.shopify_android_w19_application.model.ProductVariants
import com.example.sachinapiah.shopify_android_w19_application.model.Products
import com.example.sachinapiah.shopify_android_w19_application.networking.CallsRetrofitService
import com.example.sachinapiah.shopify_android_w19_application.networking.RetrofitService
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
                productIdsUrlSnippet = getProductItemIds(collectsResponseBody)
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
                        "Qty - " + getProductVariantQty(productItem.productItemVariants).toString(),
                        collectionItemData.collectionItemImageUrl
                    )

                )
            }
        }
    }

    /***
     * Purpose: Iterate through the product variants and sum the inventory quantity per variant of a particular product
     * which may or may not contain multiple variants
     *
     * Returns: Int -> Sum of inventory quantity of the several variants of an individual product
     */
    private fun getProductVariantQty(productItemVariants: List<ProductVariants>): Int {
        var count = 0
        productItemVariants.forEach { variant ->
            count += variant.ProductVariantsInventoryQuantity

        }
        return count
    }

    /***
     * Purpose: Iterate through the collects and append the product IDs into a string format separated by commas for
     * constructing the param during the retrofit call
     *
     * Returns: String -> product Ids, separated by commas
     */
    fun getProductItemIds(collectsResponseBody: Collects?): String {
        lateinit var productIdsSnippet: String
        collectsResponseBody?.collects?.forEachIndexed { index, it ->
            productIdsSnippet = if (index == 0) {
                it.collectProductItemProductId
            } else {
                productIdsSnippet + "," + it.collectProductItemProductId
            }
        }
        return productIdsSnippet
    }

    fun loadRecyclerView(context: Context) {
        rv_collection_detail.layoutManager = LinearLayoutManager(context)
        rv_collection_detail.adapter = CollectionDetailAdapter(itemDetailData, context)
        rv_collection_detail.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }
}