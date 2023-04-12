package com.example.foodsapp.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.foodsapp.retrofit.ApiUtils
import com.example.foodsapp.retrofit.RetrofitClient
import com.example.foodsapp.retrofit.dao.FoodDAO

//fun Navigation.go(it: View, id: Int) {
//    findNavController(it).navigate(id)
//}
//
//fun Navigation.go(it: View, direction: NavDirections) {
//    findNavController(it).navigate(direction)
//}

inline fun <reified T> getDAO(baseUrl: String): T {
    return RetrofitClient.getClient(baseUrl).create(T::class.java)
}