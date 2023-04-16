package com.example.foodsapp.data.dto

import java.io.Serializable

data class DeleteFromCartDTO(
    val cartId: Int,
    val userName: String
): Serializable