package com.core.data_03.api

import com.core.data_03.models.ProductList
import retrofit2.Call
import retrofit2.http.GET

/**
 *  This Interface defines our REST API
 *
 */
interface ProductAPI {
    @GET("/core_data/3_San_Diego_Venture_Core/json/venture_kotlin_menu_items.json")
    fun getProducts() : Call<ProductList>
}




