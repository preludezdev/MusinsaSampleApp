package com.example.musinsasampleapp.ui.main.githubtest.list

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseFragment
import com.example.musinsasampleapp.databinding.FragmentGithubListBinding
import com.example.musinsasampleapp.ui.main.githubtest.search.GithubSearchFragment.Companion.diffUtilCallback
import com.example.musinsasampleapp.vm.GithubSearchViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GithubListFragment : BaseFragment<FragmentGithubListBinding>(R.layout.fragment_github_list) {

    private val viewModel by sharedViewModel<GithubSearchViewModel>()
    private val rvAdapter by lazy { GithubListAdapter(diffUtilCallback) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        initRecyclerView()
        initCallback()
    }

    private fun initViewModel() {
        binding.vm = viewModel
    }

    private fun initRecyclerView() {
        binding.rvMyUsers.adapter = rvAdapter
    }

    private fun initCallback() {
        viewModel.myUserList.observe(this, Observer {
            rvAdapter.submitList(it)
        })
    }

    companion object {
        fun newInstance() =
            GithubListFragment()
    }
}