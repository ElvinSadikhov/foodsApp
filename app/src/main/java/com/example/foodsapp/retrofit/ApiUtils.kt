package com.example.foodsapp.retrofit

import com.example.foodsapp.retrofit.dao.CartDAO
import com.example.foodsapp.retrofit.dao.FoodItemDAO
import com.example.foodsapp.util.getDAO

class ApiUtils {

    companion object {
        fun getFoodDAO(): FoodItemDAO = getDAO()
        fun getCartDAO(): CartDAO = getDAO()
    }

}