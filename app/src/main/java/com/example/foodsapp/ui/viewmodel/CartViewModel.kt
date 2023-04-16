package com.example.foodsapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodsapp.service.AuthService
import com.example.foodsapp.data.entity.CartItem
import com.example.foodsapp.data.entity.FoodItem
import com.example.foodsapp.data.repo.CartRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale.filter
import java.util.function.Predicate
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepo: CartRepo, private val authService: AuthService): ViewModel() {
    val cartItemList = MutableLiveData<List<CartItem>>()

    fun init() {
        loadCartItems()
    }

    private fun loadCartItems() {
        CoroutineScope(Dispatchers.Main).launch {
            cartItemList.value = cartRepo.loadCartItems(authService.getUserId()!!)
        }
    }

    fun addToCart(foodItem: FoodItem, amount: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val success = cartRepo.addToCart(foodItem, amount, authService.getUserId()!!)
            if (success) {
                loadCartItems()
            }
        }
    }

    fun isInCart(foodItem: FoodItem): Boolean {
        val count = cartItemList.value
            ?.stream()
            ?.filter {
                it.name == foodItem.name
            }
            ?.count()
        return count != null && count > 0
    }

}