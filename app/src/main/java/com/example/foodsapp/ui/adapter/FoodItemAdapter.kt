package com.example.foodsapp.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.foodsapp.consts.ApiConsts.BASE_IMAGE_URL
import com.example.foodsapp.data.entity.FoodItem
import com.example.foodsapp.databinding.FoodItemDesignBinding
import com.example.foodsapp.ui.fragment.HomeTabFragmentDirections
import com.example.foodsapp.ui.viewmodel.FoodItemViewModel
import com.example.foodsapp.util.go


class FoodItemAdapter(var mContext: Context, var foodItemList: List<FoodItem>, var viewModel: FoodItemViewModel): RecyclerView.Adapter<FoodItemAdapter.CardDesignHolder>() {

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
        Glide.with(mContext)
            .load("$BASE_IMAGE_URL${foodItem.image}")
            .override(imageSize, imageSize)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    binding.imageProgressBar.visibility = View.GONE
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    binding.imageProgressBar.visibility = View.GONE
                    return false
                }
            })
            .into(binding.foodItemImageView)
    }

    fun onCardClick(view: View) {
        Navigation.go(view, HomeTabFragmentDirections.homeTabToFoodDetails())
    }

}