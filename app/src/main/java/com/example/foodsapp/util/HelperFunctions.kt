package com.example.foodsapp.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.foodsapp.consts.ApiConsts.BASE_URL
import com.example.foodsapp.retrofit.RetrofitClient

//fun Navigation.go(it: View, id: Int) {
//    findNavController(it).navigate(id)
//}
//
fun Navigation.go(it: View, direction: NavDirections) {
    findNavController(it).navigate(direction)
}

inline fun <reified T> getDAO(): T {
    return RetrofitClient.getClient(BASE_URL).create(T::class.java)
}