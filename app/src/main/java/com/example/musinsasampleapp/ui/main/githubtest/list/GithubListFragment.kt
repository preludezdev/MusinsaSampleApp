package com.example.musinsasampleapp.ui.main.githubtest.list

import android.os.Bundle
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseFragment
import com.example.musinsasampleapp.databinding.FragmentGithubListBinding
import com.example.musinsasampleapp.ui.main.githubtest.DiffCallback
import com.example.musinsasampleapp.ui.main.githubtest.GithubRvAdapter
import com.example.musinsasampleapp.vm.GithubSearchViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GithubListFragment : BaseFragment<FragmentGithubListBinding>(R.layout.fragment_github_list) {

    private val viewModel by sharedViewModel<GithubSearchViewModel>()
    private val rvAdapter by lazy {
        GithubRvAdapter(false, DiffCallback) { position, action ->
            clickEventCallback(position, action)
        }
    }

    private fun clickEventCallback(position: Int, action: Boolean) {
        val item = rvAdapter.getAdapterItem(position)
        item.checked = action
        viewModel.changeMyUserList(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        initRecyclerView()
    }

    private fun initViewModel() {
        binding.vm = viewModel
    }

    private fun initRecyclerView() {
        binding.rvMyUsers.adapter = rvAdapter
    }

    companion object {
        fun newInstance() = GithubListFragment()
    }

}