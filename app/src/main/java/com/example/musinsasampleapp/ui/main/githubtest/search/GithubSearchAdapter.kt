package com.example.musinsasampleapp.ui.main.githubtest.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseViewHolder
import com.example.musinsasampleapp.data.vo.User
import com.example.musinsasampleapp.databinding.ItemSearchUserBinding

class GithubSearchAdapter(
    diffCallback: DiffUtil.ItemCallback<User>,
    private val clickEventCallback: (position: Int, action: Boolean) -> Unit
) :
    ListAdapter<User, GithubSearchAdapter.SearchViewHolder>(diffCallback) {

    fun getAdapterItem(position: Int): User = getItem(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(clickEventCallback, parent)

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(getItem(position))

    class SearchViewHolder(
        private val clickEventCallback: (Int, Boolean) -> Unit,
        parent: ViewGroup
    ) :
        BaseViewHolder<ItemSearchUserBinding>(R.layout.item_search_user, parent) {

        init {
            binding.cbLike.setOnCheckedChangeListener(null)
            binding.cbLike.setOnCheckedChangeListener { _, isChecked ->
                clickEventCallback(adapterPosition, isChecked)
            }
        }

        fun bind(item: User) {
            binding.user = item
        }
    }
}