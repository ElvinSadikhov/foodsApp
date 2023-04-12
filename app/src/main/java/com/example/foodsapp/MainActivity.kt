package com.example.foodsapp

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var url = "http://kasimadalan.pe.hu/foods/images/$imageName"
//        Glide.with(this)
//            .load(url)
//            .override(300, 300)
//            .into(binding.imageView)
    }
}