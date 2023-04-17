package com.example.foodsapp.data.response

import com.example.foodsapp.data.entity.CartItem

data class CartItemListResponse(
    var foods_cart: List<CartItem>?
)