package com.example.foodsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.foodsapp.databinding.FragmentHomeTabBinding
import com.example.foodsapp.ui.adapter.FoodItemAdapter
import com.example.foodsapp.ui.viewmodel.FoodItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTabFragment : Fragment() {
    private lateinit var binding: FragmentHomeTabBinding
    private lateinit var foodItemViewModel: FoodItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodItemViewModel by viewModels()
        foodItemViewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeTabBinding.inflate(inflater, container, false)

        foodItemViewModel.foodItemList.observe(viewLifecycleOwner) {
            binding.rcAdapter = FoodItemAdapter(requireContext(), it, foodItemViewModel)
        }

        return binding.root
    }

}