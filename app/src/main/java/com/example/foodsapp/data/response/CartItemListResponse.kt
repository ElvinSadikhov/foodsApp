package com.example.foodsapp.data.response

import com.example.foodsapp.data.entity.CartItem

data class CartItemListResponse(
    var success: Int?,
    var message: String?,
    var foods_cart: List<CartItem>?
)