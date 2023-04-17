package com.example.foodsapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.foodsapp.consts.ApiConsts
import com.example.foodsapp.consts.ApiConsts.BASE_URL
import com.example.foodsapp.retrofit.RetrofitClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

fun Navigation.go(view: View, direction: NavDirections) {
    findNavController(view).navigate(direction)
}

inline fun <reified T> getDAO(): T {
    return RetrofitClient.getClient(BASE_URL).create(T::class.java)
}

fun ImageView.fill(context: Context, foodItemImage: String, imageSize: Int? = 0, onFail: (() -> Unit)?, onSuccess: (() -> Unit)?, ) {
    var rqBuilder = Glide.with(context)
        .load("${ApiConsts.BASE_IMAGE_URL}$foodItemImage")
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                if (onFail != null) {
                    onFail()
                }
                return false
            }
            override fun onResourceReady(drawable: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                if (onSuccess != null) {
                    onSuccess()
                }
                return false
            }
        })
    if (imageSize != null) {
        rqBuilder = rqBuilder.override(imageSize)
    }
    rqBuilder.into(this)
}


// converts a data class to a map
fun <T> T.serializeToMap(): Map<String, Any> {
    return convert()
}
// converts a map to a data class
inline fun <reified T> Map<String, Any>.toDataClass(): T {
    return convert()
}
// converts an object of type I to type O
inline fun <I, reified O> I.convert(): O {
    val gson = Gson()
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {}.type)
}
