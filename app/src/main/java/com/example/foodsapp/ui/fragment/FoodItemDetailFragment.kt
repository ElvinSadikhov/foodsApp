package com.example.foodsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.foodsapp.MainActivity
import com.example.foodsapp.R
import com.example.foodsapp.databinding.FragmentFoodItemDetailBinding
import com.example.foodsapp.ui.viewmodel.CartViewModel
import com.example.foodsapp.util.fill
import com.example.foodsapp.util.go
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodItemDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodItemDetailBinding
    private val bundle: FoodItemDetailFragmentArgs by navArgs()
    private val viewModel: CartViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFoodItemDetailBinding.inflate(inflater, container, false)

        binding.foodItemDetailFragment = this
        binding.foodItem = bundle.foodItem
        binding.imageHolder.imageView.fill(
            requireContext(),
            bundle.foodItem.image,
            onFail = {
                binding.imageHolder.progressBar.visibility = View.GONE
                binding.imageHolder.errorIcon.visibility = View.VISIBLE
            },
            onSuccess = {
                binding.imageHolder.progressBar.visibility = View.GONE
            },
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cartItemList.observe(viewLifecycleOwner) {
            val isInCart = viewModel.isInCart(bundle.foodItem)
            binding.addToCartLayer.visibility = if (isInCart) View.GONE else View.VISIBLE
            binding.viewInCartLayer.visibility = if (isInCart) View.VISIBLE else View.GONE
        }
    }

    fun onAddToCartBtnClick() {
        viewModel.addToCart(bundle.foodItem, 1) //! change
    }

    fun viewInCartBtnClick(view: View) {
        Navigation.go(view, FoodItemDetailFragmentDirections.foodDetailToCartTab())
    }

}