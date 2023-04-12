package com.example.foodsapp.data.entity

import java.io.Serializable

data class FoodItem(
    val id: Int,
    val name: String,
    val image: String,
    val price: Int,
    val category: String
): Serializable