package com.example.foodsapp.data.datasource

import android.util.Log
import com.example.foodsapp.data.dto.AddToCartDTO
import com.example.foodsapp.data.dto.DeleteFromCartDTO
import com.example.foodsapp.data.dto.LoadCartItemsDTO
import com.example.foodsapp.data.entity.CartItem
import com.example.foodsapp.data.response.CRUDResponse
import com.example.foodsapp.data.response.CartItemListResponse
import com.example.foodsapp.retrofit.dao.CartDAO
import com.example.foodsapp.util.convert
import com.example.foodsapp.util.serializeToMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartDatasource(var cartDAO: CartDAO) {

    suspend fun loadCartItems(userName: String): List<CartItem> =
        withContext(Dispatchers.IO) {
            cartDAO.loadCartItems(LoadCartItemsDTO(userName).serializeToMap())?.foods_cart ?: listOf()
        }

    suspend fun addToCart(addToCartDTO: AddToCartDTO): Boolean =
        withContext(Dispatchers.IO) {
            isSuccessful(cartDAO.addToCart(addToCartDTO.serializeToMap()))
        }

    suspend fun deleteFromCart(deleteFromCartDTO: DeleteFromCartDTO): Boolean =
        withContext(Dispatchers.IO) {
            isSuccessful(cartDAO.deleteFromCart(deleteFromCartDTO.serializeToMap()))
        }

    private fun isSuccessful(crudResponse: CRUDResponse): Boolean = crudResponse.success == 1

}