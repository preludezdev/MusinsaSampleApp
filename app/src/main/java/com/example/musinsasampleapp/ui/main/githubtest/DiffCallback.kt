package com.example.musinsasampleapp.ui.main.githubtest

import androidx.recyclerview.widget.DiffUtil
import com.example.musinsasampleapp.data.vo.User

object DiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.login == newItem.login && oldItem.score == newItem.score
    }

}