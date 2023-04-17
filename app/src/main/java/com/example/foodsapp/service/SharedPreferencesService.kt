package com.example.foodsapp.service

import android.content.SharedPreferences
import com.example.foodsapp.consts.SharedPreferencesConsts
import com.example.foodsapp.data.enums.AppTheme
import com.example.foodsapp.data.enums.LocaleType

class SharedPreferencesService(private val sharedPreferences: SharedPreferences) {

    fun getLocale(): LocaleType? {
        val localeCode = sharedPreferences.getString(SharedPreferencesConsts.localeKey, "--")
        return if(localeCode == "--") null else LocaleType.values().find { it.code == localeCode }
    }

    fun setLocale(localeType: LocaleType) {
        sharedPreferences.edit().putString(SharedPreferencesConsts.localeKey, localeType.code).apply()
    }

    fun getTheme(): AppTheme? {
        val themeMode = sharedPreferences.getInt(SharedPreferencesConsts.themeKey, -100)
        return if(themeMode == -100) null else AppTheme.values().find { it.mode == themeMode }
    }

    fun setTheme(theme: AppTheme) {
        sharedPreferences.edit().putInt(SharedPreferencesConsts.themeKey, theme.mode).apply()
    }

}
