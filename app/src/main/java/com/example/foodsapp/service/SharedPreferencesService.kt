package com.example.foodsapp.service

import android.content.SharedPreferences
import com.example.foodsapp.consts.SharedPreferencesConsts
import com.example.foodsapp.data.enums.AppTheme
import com.example.foodsapp.data.enums.LocaleType

class SharedPreferencesService(private val sharedPreferences: SharedPreferences) {
    private val defaultLocaleType = LocaleType.ENGLISH
    private val defaultTheme = AppTheme.SYSTEM

    fun getLocale(): LocaleType {
        val localeCode = sharedPreferences.getString(SharedPreferencesConsts.localeKey, defaultLocaleType.code)
        return LocaleType.values().find { it.code == localeCode } ?: defaultLocaleType
    }

    fun setLocale(localeType: LocaleType) {
        sharedPreferences.edit().putString(SharedPreferencesConsts.localeKey, localeType.code).apply()
    }

    fun getTheme(): AppTheme {
        val themeMode = sharedPreferences.getInt(SharedPreferencesConsts.themeKey, defaultTheme.mode)
        return AppTheme.values().find { it.mode == themeMode } ?: defaultTheme
    }

    fun setTheme(theme: AppTheme) {
        sharedPreferences.edit().putInt(SharedPreferencesConsts.themeKey, theme.mode).apply()
    }

}
