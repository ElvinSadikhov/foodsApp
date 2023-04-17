package com.example.foodsapp.data.response

import com.example.foodsapp.data.entity.FoodItem

data class FoodItemListResponse(
    var foods: List<FoodItem>?
)