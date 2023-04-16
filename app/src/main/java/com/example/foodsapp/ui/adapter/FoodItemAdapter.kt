package com.example.foodsapp.ui.adapter

import android.content.Context
import android.view.*
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsapp.data.entity.FoodItem
import com.example.foodsapp.databinding.FoodItemDesignBinding
import com.example.foodsapp.ui.fragment.HomeTabFragmentDirections
import com.example.foodsapp.ui.viewmodel.FoodItemViewModel
import com.example.foodsapp.util.go
import com.example.foodsapp.util.fill


class FoodItemAdapter(var mContext: Context, var foodItemList: List<FoodItem>): RecyclerView.Adapter<FoodItemAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(var binding: FoodItemDesignBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding: FoodItemDesignBinding = FoodItemDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardDesignHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodItemList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val foodItem = foodItemList[position]
        val binding = holder.binding
        binding.foodItem = foodItem
        binding.foodItemAdapter = this

        val imageSize = (mContext.resources.displayMetrics.widthPixels / 2.7).toInt()
        binding.foodItemImageView.fill(
            mContext,
            foodItem.image,
            imageSize,
            onFail = {
                binding.imageProgressBar.visibility = View.GONE
                binding.errorImage.visibility = View.VISIBLE
            },
            onSuccess = {
                binding.imageProgressBar.visibility = View.GONE
            },
        )
    }

    fun onCardClick(view: View, foodItem: FoodItem) {
        Navigation.go(view, HomeTabFragmentDirections.homeTabToFoodDetails(foodItem))
    }

}