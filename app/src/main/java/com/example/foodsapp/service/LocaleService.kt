package com.example.foodsapp.service

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Log
import com.example.foodsapp.consts.LogTags
import com.example.foodsapp.data.enums.LocaleType
import java.util.*

@Suppress("DEPRECATION")
@SuppressLint("ObsoleteSdkInt")
class LocaleService private constructor() {
    companion object {
        fun getInstance(): LocaleService = LocaleService()
        private val defaultLocaleType = LocaleType.ENGLISH
    }

    fun getLocale(context: Context): LocaleType? = SharedPreferencesService.getLocale(context)

    fun setChosenLocale(context: Context): ContextWrapper =
        setLocale(context, getLocale(context) ?: defaultLocaleType)

    fun setLocale(context: Context, localeType: LocaleType): ContextWrapper {
        var newContext = context

        val resources = context.resources
        val config = resources.configuration
        val systemLocale =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) config.locales.get(0) else config.locale
        if (!systemLocale.language.equals(localeType.code)) {
            val locale = Locale(localeType.code)
            Locale.setDefault(locale)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                config.setLocale(locale)
            else
                config.locale = locale
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                newContext = context.createConfigurationContext(config)
            else {
                newContext.resources.updateConfiguration(config, resources.displayMetrics)
            }

        }
        SharedPreferencesService.setLocale(context, localeType)
        Log.d(LogTags.appLocale, localeType.code)

        return ContextWrapper(newContext)
    }
}
