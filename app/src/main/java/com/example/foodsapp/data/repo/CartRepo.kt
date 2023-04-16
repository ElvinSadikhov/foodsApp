package com.example.foodsapp.data.repo

import com.example.foodsapp.data.datasource.CartDatasource
import com.example.foodsapp.data.dto.AddToCartDTO
import com.example.foodsapp.data.dto.DeleteFromCartDTO
import com.example.foodsapp.data.entity.CartItem
import com.example.foodsapp.data.entity.FoodItem

class CartRepo(var cartDatasource: CartDatasource) {

    suspend fun loadCartItems(userName: String): List<CartItem> = cartDatasource.loadCartItems(userName)

    suspend fun addToCart(foodItem: FoodItem, orderAmount: Int, userName: String): Boolean
        = cartDatasource.addToCart(
            AddToCartDTO(
                foodItem.name,
                foodItem.image,
                foodItem.price,
                foodItem.category,
                orderAmount,
                userName
            )
        )
    suspend fun deleteFromCart(
        cartItemId: Int,
        userName: String
    ): Boolean
        = cartDatasource.deleteFromCart(
            DeleteFromCartDTO(
                cartItemId,
                userName
            )
        )
}