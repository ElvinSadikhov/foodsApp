package com.example.foodsapp.service

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import android.util.Log
import com.example.foodsapp.consts.LogTags
import com.example.foodsapp.data.enums.LocaleType
import java.util.*

@Suppress("DEPRECATION")
class LocaleService(private val sharedPreferencesService: SharedPreferencesService, private val context: Context) {

    fun getLocale(): LocaleType? = sharedPreferencesService.getLocale()

    private fun isLocaleSelected(): Boolean = sharedPreferencesService.getLocale() != null

    fun setDefaultIfNeeded() {
        if(!isLocaleSelected()) {
            setLocale(LocaleType.ENGLISH)
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    fun setLocale(localeType: LocaleType) {
//        val newLocale = Locale(localeType.code)
//        Locale.setDefault(newLocale)
//        val config = resources.configuration
//        config.setLocale(newLocale)
//        resources.updateConfiguration(config, resources.displayMetrics)

//        val locale = Locale(localeType.code)
//        Locale.setDefault(locale)
//        val configuration = resources.configuration
//        configuration.locale = locale
//        configuration.setLayoutDirection(locale)
//        resources.updateConfiguration(configuration, resources.displayMetrics)

        val config = context.resources.configuration
        val lang = localeType.code
        val locale = Locale(lang)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            config.setLocale(locale)
        else
            config.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)


        sharedPreferencesService.setLocale(localeType)
        Log.d(LogTags.appLocale, localeType.code)
    }


    fun setLanguage(context: Context, localeType: LocaleType): ContextWrapper {
        var mContext = context

        val localeLang = localeType.code.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val locale = if (localeLang.size > 1)
            Locale(localeLang[0], localeLang[1])
        else
            Locale(localeLang[0])

        val res = mContext.resources
        val configuration = res.configuration

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(locale)
            LocaleList.setDefault(localeList)
            configuration.setLocales(localeList)
            mContext = mContext.createConfigurationContext(configuration)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale)
            mContext = mContext.createConfigurationContext(configuration)
        } else {
            configuration.locale = locale
            res.updateConfiguration(configuration, res.displayMetrics)
        }

        return ContextWrapper(mContext)
    }

}
