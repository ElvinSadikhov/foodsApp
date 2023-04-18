package com.example.foodsapp.service

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.foodsapp.consts.LogTags
import com.example.foodsapp.data.enums.AppTheme

class ThemeService(private val context: Context) {

    private fun isThemeSelected(): Boolean = getTheme() != null

    fun setDefaultIfNeeded() {
        if(!isThemeSelected()) {
            setTheme(AppTheme.SYSTEM)
        }
    }

    fun getTheme(): AppTheme? = SharedPreferencesService.getTheme(context)

    fun setTheme(theme: AppTheme) {
        AppCompatDelegate.setDefaultNightMode(theme.mode)
        SharedPreferencesService.setTheme(context, theme)
        Log.d(LogTags.appTheme, theme.name)
    }
}