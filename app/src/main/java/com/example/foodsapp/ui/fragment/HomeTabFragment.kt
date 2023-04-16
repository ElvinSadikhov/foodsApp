package com.example.foodsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.foodsapp.databinding.FragmentHomeTabBinding
import com.example.foodsapp.ui.adapter.FoodItemAdapter
import com.example.foodsapp.ui.viewmodel.FoodItemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTabFragment : Fragment() {
    private lateinit var binding: FragmentHomeTabBinding
    private val viewModel: FoodItemViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeTabBinding.inflate(inflater, container, false)

        viewModel.foodItemList.observe(viewLifecycleOwner) {
            binding.rcAdapter = FoodItemAdapter(requireContext(), it)
        }

        return binding.root
    }

}