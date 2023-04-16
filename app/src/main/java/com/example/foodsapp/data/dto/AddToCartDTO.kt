package com.example.foodsapp.data.dto

import java.io.Serializable

data class AddToCartDTO(
    val name: String,
    val image: String,
    val price: Double,
    val category: String,
    val orderAmount: Int,
    val userName: String
): Serializable