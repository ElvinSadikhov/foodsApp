package com.example.foodsapp.util

import com.example.foodsapp.retrofit.RetrofitClient

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