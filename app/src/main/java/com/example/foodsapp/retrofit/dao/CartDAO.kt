package com.example.foodsapp.retrofit.dao

import com.example.foodsapp.data.response.CRUDResponse
import com.example.foodsapp.data.response.CartItemListResponse
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CartDAO {

    @POST("/foods/getFoodsCart.php")
    @FormUrlEncoded
    @JvmSuppressWildcards
    suspend fun loadCartItems(@FieldMap map: Map<String, Any>): CartItemListResponse

    @POST("/foods/insertFood.php")
    @FormUrlEncoded
    @JvmSuppressWildcards
    suspend fun addToCart(@FieldMap map: Map<String, Any>): CRUDResponse

    @POST("/foods/deleteFood.php")
    @FormUrlEncoded
    @JvmSuppressWildcards
    suspend fun deleteFromCart(@FieldMap map: Map<String, Any>): CRUDResponse

}