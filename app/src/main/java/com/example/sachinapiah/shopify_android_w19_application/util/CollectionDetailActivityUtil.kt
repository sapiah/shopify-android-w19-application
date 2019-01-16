package com.example.sachinapiah.shopify_android_w19_application.util

import com.example.sachinapiah.shopify_android_w19_application.model.Collects
import com.example.sachinapiah.shopify_android_w19_application.model.ProductVariant

object CollectionDetailActivityUtil {

    /***
     * Purpose: Iterate through the product variants and sum the inventory quantity per variant of a particular product
     * which may or may not contain multiple variants
     *
     * Returns: Int -> Sum of inventory quantity of the several variants of an individual product
     */
    fun getProductVariantQty(productItemVariants: List<ProductVariant>): Int {
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

}