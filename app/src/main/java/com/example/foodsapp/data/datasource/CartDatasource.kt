package com.example.foodsapp.data.datasource

import android.util.Log
import com.example.foodsapp.data.dto.AddToCartDTO
import com.example.foodsapp.data.dto.LoadCartItemsDTO
import com.example.foodsapp.data.entity.CartItem
import com.example.foodsapp.retrofit.dao.CartDAO
import com.example.foodsapp.util.serializeToMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartDatasource(var cartDAO: CartDAO) {

    suspend fun loadCartItems(userName: String): List<CartItem> =
        withContext(Dispatchers.IO) {
            Log.d("s", "${LoadCartItemsDTO(userName).serializeToMap()}")
            val res =
                cartDAO.loadCartItems(LoadCartItemsDTO(userName).serializeToMap()).foods_cart
            Log.d("s", "$res")
            return@withContext res
        }

    suspend fun addToCart(addToCartDTO: AddToCartDTO): Boolean =
        withContext(Dispatchers.IO) {
            val res = cartDAO.addToCart(addToCartDTO.serializeToMap())
            val statusCode = res.success
            Log.d("s", "${addToCartDTO.serializeToMap()}")
            Log.d("s", "$res.success $res.message")
            statusCode == 1
        }

}