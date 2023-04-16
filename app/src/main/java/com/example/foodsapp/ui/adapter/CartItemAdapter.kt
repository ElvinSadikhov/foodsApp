package com.example.foodsapp.ui.adapter

import android.content.Context
import android.view.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodsapp.data.entity.CartItem
import com.example.foodsapp.data.entity.FoodItem
import com.example.foodsapp.databinding.CartItemDesignBinding
import com.example.foodsapp.ui.fragment.CartTabFragmentDirections
import com.example.foodsapp.ui.fragment.FoodItemDetailFragmentDirections
import com.example.foodsapp.ui.fragment.HomeTabFragmentDirections
import com.example.foodsapp.util.go
import com.example.foodsapp.util.fill


class CartItemAdapter(var mContext: Context, var cartItemList: List<CartItem>): RecyclerView.Adapter<CartItemAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(var binding: CartItemDesignBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding = CartItemDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardDesignHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartItemList.size
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val cartItem = cartItemList[position]
        val binding = holder.binding
        binding.cartItem = cartItem
        binding.cartItemAdapter = this

        val imageSize = (mContext.resources.displayMetrics.widthPixels / 2.7).toInt()
        binding.imageHolder.imageView.fill(
            mContext,
            cartItem.image,
            imageSize,
            onFail = {
                binding.imageHolder.progressBar.visibility = View.GONE
                binding.imageHolder.errorIcon.visibility = View.VISIBLE
            },
            onSuccess = {
                binding.imageHolder.progressBar.visibility = View.GONE
            },
        )
        binding.counterLayout.counterTv.text = cartItem.orderAmount.toString()
    }

    fun onDeleteIconClick(cartItem: CartItem) {
        TODO("implement")
    }

}