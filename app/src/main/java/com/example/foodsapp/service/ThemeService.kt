package com.example.foodsapp.service

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.foodsapp.consts.LogTags
import com.example.foodsapp.data.enums.AppTheme

class ThemeService private constructor() {
    companion object {
        fun setDefaultIfNeeded(context: Context) = setTheme(context, getTheme(context) ?: AppTheme.SYSTEM)

        fun getTheme(context: Context): AppTheme? = SharedPreferencesService.getTheme(context)

        fun setTheme(context: Context, theme: AppTheme) {
            AppCompatDelegate.setDefaultNightMode(theme.mode)
            SharedPreferencesService.setTheme(context, theme)
            Log.d(LogTags.appTheme, theme.name)
        }
    }
}