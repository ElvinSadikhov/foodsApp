package com.example.foodsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodsapp.data.enums.AppTheme
import com.example.foodsapp.databinding.ActivityMainBinding
import com.example.foodsapp.service.ThemeService
import com.example.foodsapp.ui.fragment.*
import com.example.foodsapp.ui.viewmodel.CartViewModel
import com.example.foodsapp.ui.viewmodel.FoodItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var themeService: ThemeService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeService.setTheme(AppTheme.SYSTEM)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setupBottomNavBar()
        setContentView(binding.root)
    }

    private fun setupBottomNavBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavBar, navHostFragment.navController)   // we need to have same ids for fragments(nav file) and menu items(menu file)

        val navController = navHostFragment.navController
        binding.bottomNavBar.setOnItemSelectedListener {
            if (it.itemId == navController.currentDestination?.id) {
                if (supportFragmentManager.backStackEntryCount > 1) {
                    navController.popBackStack(navController.graph.startDestinationId, false)
                }
            } else {
                navController.navigate(it.itemId)
            }
            true
        }
    }

    fun showBottomNavBar() {
        binding.bottomNavBar.visibility = View.VISIBLE
    }

}