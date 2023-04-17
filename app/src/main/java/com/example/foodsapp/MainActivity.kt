package com.example.foodsapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodsapp.data.enums.AppTheme
import com.example.foodsapp.data.enums.LocaleType
import com.example.foodsapp.databinding.ActivityMainBinding
import com.example.foodsapp.service.LocaleService
import com.example.foodsapp.service.SharedPreferencesService
import com.example.foodsapp.service.ThemeService
import com.google.android.gms.common.internal.Constants
import com.google.android.gms.common.internal.service.Common
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var themeService: ThemeService
    @Inject lateinit var localeService: LocaleService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeService.setDefaultIfNeeded()
        localeService.setDefaultIfNeeded()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setupBottomNavBar()
        setContentView(binding.root)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        setupBottomNavBar()
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onRestart() {
        super.onRestart()
        setupBottomNavBar()
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

//    fun restartActivity() {
//        val intent = intent
//        finish()
//        startActivity(intent)
//    }

    fun showBottomNavBar() {
        binding.bottomNavBar.visibility = View.VISIBLE
    }

    fun hideBottomNavBar() {
        binding.bottomNavBar.visibility = View.GONE
    }

}