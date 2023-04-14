package com.example.foodsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodsapp.R
import com.example.foodsapp.databinding.FragmentHomeTabBinding
import com.example.foodsapp.databinding.FragmentProfileTabBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTabFragment : Fragment() {
    private lateinit var binding: FragmentHomeTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeTabBinding.inflate(inflater, container, false)
        return binding.root
    }

}