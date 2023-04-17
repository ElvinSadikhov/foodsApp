package com.example.foodsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodsapp.consts.LogTags
import com.example.foodsapp.data.enums.LocaleType
import com.example.foodsapp.databinding.FragmentProfileTabBinding
import com.example.foodsapp.service.LocaleService
import com.example.foodsapp.service.ThemeService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileTabFragment : Fragment() {
    private lateinit var binding: FragmentProfileTabBinding
    @Inject lateinit var localeService: LocaleService
    @Inject lateinit var themeService: ThemeService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileTabBinding.inflate(inflater, container, false)
        binding.profileTabFragment = this
        return binding.root
    }

    fun onClick() {
        TODO("implement")
//        localeService.setLocaleType(LocaleType.ENGLISH)
//        recreateFragment()
    }

    private fun recreateFragment() {
        parentFragmentManager.beginTransaction()
            .detach(this).attach(this)
            .commit()
    }

}