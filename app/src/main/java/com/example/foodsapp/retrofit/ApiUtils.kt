package com.example.foodsapp.retrofit

import com.example.foodsapp.retrofit.dao.CartDAO
import com.example.foodsapp.retrofit.dao.FoodDAO
import com.example.foodsapp.util.getDAO

class ApiUtils {

    companion object {
        private const val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodDAO(): FoodDAO = getDAO(BASE_URL)
        fun getCartDAO(): CartDAO = getDAO(BASE_URL)
    }

}