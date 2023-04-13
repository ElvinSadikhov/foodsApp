package com.example.foodsapp.data.entity

import java.io.Serializable

data class CartItem(
    val cartId: Int,
    val name: String,
    val image: String,
    val price: Double,
    val category: String,
    val orderAmount: Int,
    val userName: String
): Serializable