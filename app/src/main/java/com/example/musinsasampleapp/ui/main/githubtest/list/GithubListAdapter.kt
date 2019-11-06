package com.example.musinsasampleapp.ui.main.githubtest.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseViewHolder
import com.example.musinsasampleapp.data.vo.User
import com.example.musinsasampleapp.databinding.ItemListUserBinding

class GithubListAdapter(diffCallback: DiffUtil.ItemCallback<User>) :
    ListAdapter<User, GithubListAdapter.SearchViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(parent)

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(getItem(position))

    class SearchViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemListUserBinding>(R.layout.item_list_user, parent) {

        fun bind(item: User) {
            binding.user = item
        }
    }
}