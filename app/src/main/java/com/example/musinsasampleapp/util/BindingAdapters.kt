package com.example.musinsasampleapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.data.vo.User
import com.example.musinsasampleapp.ui.main.githubtest.GithubRvAdapter

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

@BindingAdapter("submitList")
fun RecyclerView.submitList(list: List<User>?) {
    (adapter as? GithubRvAdapter)?.submitList(list?.toMutableList())
}