package com.gomaa.marvelapp.base.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gomaa.marvelapp.R

fun loadImage(context: Context, imageUrl: String, imageView: ImageView) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(R.drawable.image_placeholder)
        .into(imageView)
}