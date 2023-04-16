package com.example.foodsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.foodsapp.R
import com.example.foodsapp.databinding.FragmentCartTabBinding
import com.example.foodsapp.databinding.FragmentProfileTabBinding
import com.example.foodsapp.ui.adapter.CartItemAdapter
import com.example.foodsapp.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartTabFragment : Fragment() {
    private lateinit var binding: FragmentCartTabBinding
    private val viewModel: CartViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartTabBinding.inflate(inflater, container, false)

        viewModel.cartItemList.observe(viewLifecycleOwner) {
            binding.rcAdapter = CartItemAdapter(requireContext(), it)
        }

        return binding.root
    }

}