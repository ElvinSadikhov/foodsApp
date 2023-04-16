package com.example.foodsapp.ui.viewmodel

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
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepo: CartRepo, private val authService: AuthService): ViewModel() {
    val cartItemList = MutableLiveData<List<CartItem>>()

    fun init() {
        // there is a huge problem with backend response, so I needed to do a work around
        CoroutineScope(Dispatchers.Main).launch {
            val success = cartRepo.addToCart(FoodItem(5, "Pasta", "pasta.png", 6.0, "Meals"), 1, authService.getUserId()!!)
            if (success) {
                val cartItems = cartRepo.loadCartItems(authService.getUserId()!!)
                cartRepo.deleteFromCart(cartItems[0].cartId, authService.getUserId()!!)
            }
        }
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
            if (success)    loadCartItems()
        }
    }

    fun deleteFromCart(cartItem: CartItem) {
        CoroutineScope(Dispatchers.Main).launch {
            val success = cartRepo.deleteFromCart(cartItem.cartId, authService.getUserId()!!)
            if (success) {
                // there is a huge problem with backend response, so I needed to do a work around
                if (cartItemList.value?.size != 1) {
                    loadCartItems()
                } else {
                    cartItemList.value = listOf()
                }
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