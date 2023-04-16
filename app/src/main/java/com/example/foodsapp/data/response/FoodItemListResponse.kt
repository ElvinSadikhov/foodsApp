package com.example.foodsapp.data.response

import com.example.foodsapp.data.entity.FoodItem

data class FoodItemListResponse(
    var success: Int?,
    var messag: String?,
    var foods: List<FoodItem>?
)