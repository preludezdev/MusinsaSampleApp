package com.example.musinsasampleapp.ui.main.githubtest

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseViewHolder
import com.example.musinsasampleapp.data.vo.User
import com.example.musinsasampleapp.databinding.ItemSearchUserBinding

class GithubRvAdapter(
    private val isSearchMode: Boolean,
    diffCallback: DiffUtil.ItemCallback<User>,
    private val clickEventCallback: (position: Int, action: Boolean) -> Unit
) :
    ListAdapter<User, GithubRvAdapter.SearchViewHolder>(diffCallback) {

    fun getAdapterItem(position: Int): User = getItem(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(isSearchMode, clickEventCallback, parent)

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(getItem(position))

    class SearchViewHolder(
        isSearchMode: Boolean,
        private val clickEventCallback: (Int, Boolean) -> Unit,
        parent: ViewGroup
    ) :
        BaseViewHolder<ItemSearchUserBinding>(R.layout.item_search_user, parent) {

        init {
            if (isSearchMode) {
                binding.cbLike.setOnCheckedChangeListener { _, isChecked ->
                    clickEventCallback(adapterPosition, isChecked)
                }
            } else {
                binding.cbLike.isClickable = false
            }
        }

        fun bind(item: User) {
            binding.user = item
        }
    }
}