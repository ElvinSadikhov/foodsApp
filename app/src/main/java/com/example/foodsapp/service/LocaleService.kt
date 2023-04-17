package com.example.foodsapp.service

import android.content.res.Resources
import android.util.Log
import com.example.foodsapp.consts.LogTags
import com.example.foodsapp.data.enums.AppTheme
import com.example.foodsapp.data.enums.LocaleType
import java.util.*

class LocaleService(private val sharedPreferencesService: SharedPreferencesService, private val resources: Resources) {

    fun getCurrentLocaleType(): LocaleType = sharedPreferencesService.getLocale()

    fun setLocaleType(localeType: LocaleType) {
        val newLocale = Locale(localeType.code)
        Locale.setDefault(newLocale)
        val config = resources.configuration
        config.setLocale(newLocale)
        resources.updateConfiguration(config, resources.displayMetrics)
        sharedPreferencesService.setLocale(localeType)
        Log.d(LogTags.appLocale, localeType.code)
    }

}
