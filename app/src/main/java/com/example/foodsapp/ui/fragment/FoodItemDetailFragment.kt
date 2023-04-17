package com.example.foodsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
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
    private var counterValue = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFoodItemDetailBinding.inflate(inflater, container, false)

        binding.foodItemDetailFragment = this
        binding.foodItem = bundle.foodItem
        binding.counterValue = counterValue
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
        viewModel.addToCart(bundle.foodItem, counterValue) //! change
    }

    fun viewInCartBtnClick(view: View) {
        Navigation.go(view, FoodItemDetailFragmentDirections.foodDetailToCartTab())
    }

    fun incrementCounter() {
        if (counterValue >= 9)   return
        binding.counterValue = ++counterValue
    }
    fun decrementCounter() {
        if (counterValue <= 1)   return
        binding.counterValue = --counterValue
    }

}