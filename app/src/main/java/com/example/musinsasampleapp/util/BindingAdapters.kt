package com.example.musinsasampleapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.musinsasampleapp.R

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(url: String?) {
    if (url.isNullOrEmpty()) return

    Glide
        .with(this)
        .load(url)
        .placeholder(R.drawable.loading)
        .error(R.drawable.error)
        .into(this)
}