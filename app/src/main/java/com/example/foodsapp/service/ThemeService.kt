package com.example.foodsapp.service

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.foodsapp.consts.LogTags
import com.example.foodsapp.data.enums.AppTheme

class ThemeService(private val sharedPreferencesService: SharedPreferencesService) {

    fun getTheme(): AppTheme = sharedPreferencesService.getTheme()

    fun setTheme(theme: AppTheme) {
        AppCompatDelegate.setDefaultNightMode(theme.mode)
        sharedPreferencesService.setTheme(theme)
        Log.d(LogTags.appTheme, theme.name)
    }
}