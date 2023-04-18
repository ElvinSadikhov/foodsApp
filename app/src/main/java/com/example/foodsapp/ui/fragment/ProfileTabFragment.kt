package com.example.foodsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.transition.AutoTransition
import android.transition.TransitionManager
import com.example.foodsapp.MainActivity
import com.example.foodsapp.R
import com.example.foodsapp.data.enums.AppTheme
import com.example.foodsapp.data.enums.LocaleType
import com.example.foodsapp.databinding.FragmentProfileTabBinding
import com.example.foodsapp.service.LocaleService
import com.example.foodsapp.service.ThemeService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileTabFragment : Fragment() {
    private lateinit var binding: FragmentProfileTabBinding
    @Inject lateinit var themeService: ThemeService
    private val localeService = LocaleService.getInstance()
    private lateinit var localeTypeBtnIdPairs: List<Pair<LocaleType, Int>>
    private lateinit var themeBtnIdPairs: List<Pair<AppTheme, Int>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileTabBinding.inflate(inflater, container, false)
        binding.apply {
            localeTypeBtnIdPairs = listOf(Pair(LocaleType.ENGLISH, binding.englishRadioBtn.id), Pair(LocaleType.RUSSIAN, binding.russianRadioBtn.id), Pair(LocaleType.AZERBAIJANI, binding.azerbaijaniRadioBtn.id))
            themeBtnIdPairs = listOf(Pair(AppTheme.LIGHT, binding.lightThemeRadioBtn.id), Pair(AppTheme.DARK, binding.darkThemeRadioBtn.id), Pair(AppTheme.SYSTEM, binding.systemThemeRadioBtn.id))
            profileTabFragment = this@ProfileTabFragment
            localeRadioGroup.check(localeTypeBtnIdPairs.find { it.first == localeService.getLocale(requireContext()) }!!.second)
            themeRadioGroup.check(themeBtnIdPairs.find { it.first == themeService.getTheme() }!!.second)
        }
        return binding.root
    }

    fun toggleExpandableView(button: View, expandable: View) {
        val isCollapsed = expandable.visibility == View.GONE
        TransitionManager.beginDelayedTransition(binding.expandableArea, AutoTransition())
        expandable.visibility = if(isCollapsed) View.VISIBLE else View.GONE
        button.animate().rotationBy(if(isCollapsed) -180F else 180F).start()
    }

    fun onLocaleChanged() {
        localeService.setLocale(requireContext(), localeTypeBtnIdPairs.find { it.second == binding.localeRadioGroup.checkedRadioButtonId }!!.first)
        (activity as MainActivity).recreate()
    }

    fun onThemeChanged() {
        themeService.setTheme(themeBtnIdPairs.find { it.second == binding.themeRadioGroup.checkedRadioButtonId }!!.first)
    }

    private fun reloadFragment() {
        val currentFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.profileTabFragment)!!

        requireActivity().supportFragmentManager
            .beginTransaction()
            .detach(currentFragment)
            .commit()

        requireActivity().supportFragmentManager
            .beginTransaction()
            .attach(currentFragment)
            .commit()
    }

}