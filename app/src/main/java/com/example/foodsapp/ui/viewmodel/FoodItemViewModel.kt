package com.example.foodsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodsapp.data.entity.FoodItem
import com.example.foodsapp.data.repo.FoodItemRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodItemViewModel @Inject constructor(var foodItemRepo: FoodItemRepo): ViewModel() {
    var foodItemList = MutableLiveData<List<FoodItem>>()

    init {
        loadFoodItemList()
    }

    private fun loadFoodItemList() {
        CoroutineScope(Dispatchers.Main).launch {
            foodItemList.value = foodItemRepo.loadFoodItems()
        }
    }

}