package com.example.sachinapiah.shopify_android_w19_application.util

import com.example.sachinapiah.shopify_android_w19_application.model.CollectProductItem
import com.example.sachinapiah.shopify_android_w19_application.model.Collects
import com.example.sachinapiah.shopify_android_w19_application.model.ProductVariant
import com.example.sachinapiah.shopify_android_w19_application.util.CollectionDetailActivityUtil.getProductItemIds
import com.example.sachinapiah.shopify_android_w19_application.util.CollectionDetailActivityUtil.getProductVariantQty
import junit.framework.Assert.assertEquals
import org.junit.Test


class CollectionDetailActivityUtilTest {

    @Test
    fun `when product variants list contains two variants of 5 qty each expect sum of 10`() {
        val productVariants = mutableListOf(
            ProductVariant(123456789, 5),
            ProductVariant(987654321, 5)
        )
        assertEquals(10, getProductVariantQty(productVariants))
    }

    @Test
    fun `when collects containing multiple product ids are received generate id snippet for url`() {
        val collects =
            Collects(
                listOf(
                    CollectProductItem(123456789, "1234"),
                    CollectProductItem(987654321, "4321"),
                    CollectProductItem(214365709, "5678"),
                    CollectProductItem(987654321, "8765")
                )
            )
        assertEquals("1234,4321,5678,8765", getProductItemIds(collects))
    }
}

