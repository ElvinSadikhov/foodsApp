package com.example.foodsapp.service

import android.content.Context
import android.content.SharedPreferences
import com.example.foodsapp.consts.SharedPreferencesConsts
import com.example.foodsapp.data.enums.AppTheme
import com.example.foodsapp.data.enums.LocaleType

class SharedPreferencesService {
    companion object {
        private fun getSPInstance(context: Context): SharedPreferences = context.getSharedPreferences(SharedPreferencesConsts.name, Context.MODE_PRIVATE)
        private const val defValue = "--"

        fun getLocale(context: Context): LocaleType? {
            val localeCode = getSPInstance(context).getString(SharedPreferencesConsts.localeKey, defValue)
            return if(localeCode == defValue) null else LocaleType.values().find { it.code == localeCode }
        }

        fun setLocale(context: Context, localeType: LocaleType) {
            getSPInstance(context).edit().putString(SharedPreferencesConsts.localeKey, localeType.code).apply()
        }

        fun getTheme(context: Context): AppTheme? {
            val themeMode = getSPInstance(context).getString(SharedPreferencesConsts.themeKey, defValue)!!
            return if(themeMode == defValue) null else AppTheme.values().find { it.mode == themeMode.toInt() }
        }

        fun setTheme(context: Context, theme: AppTheme) {
            getSPInstance(context).edit().putString(SharedPreferencesConsts.themeKey, theme.mode.toString()).apply()
        }
    }
}
