package com.example.foodsapp

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.foodsapp.databinding.ActivityMainBinding
import com.example.foodsapp.service.LocaleService
import com.example.foodsapp.service.ThemeService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var themeService: ThemeService
    private val localeService = LocaleService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeService.setDefaultIfNeeded()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setupBottomNavBar()
        setContentView(binding.root)
    }

    override fun attachBaseContext(newBase: Context?) {
        if (newBase == null)    return
        super.attachBaseContext(localeService.setChosenLocale(newBase))
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

    fun showBottomNavBar() {
        binding.bottomNavBar.visibility = View.VISIBLE
    }

    fun hideBottomNavBar() {
        binding.bottomNavBar.visibility = View.GONE
    }

}