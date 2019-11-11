package com.example.musinsasampleapp.ui.main.githubtest.search

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseFragment
import com.example.musinsasampleapp.databinding.FragmentGithubSearchBinding
import com.example.musinsasampleapp.ui.main.githubtest.GithubRvAdapter
import com.example.musinsasampleapp.ui.main.githubtest.UserDiffCallback
import com.example.musinsasampleapp.vm.GithubSearchViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GithubSearchFragment :
    BaseFragment<FragmentGithubSearchBinding>(R.layout.fragment_github_search) {

    private val viewModel by sharedViewModel<GithubSearchViewModel>()
    private val rvAdapter by lazy {
        GithubRvAdapter(true, UserDiffCallback) { position, action ->
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
        initEvent()
        initCallback()
    }

    private fun initViewModel() {
        binding.vm = viewModel
    }

    private fun initRecyclerView() {
        binding.rvUsers.adapter = rvAdapter
    }

    private fun initEvent() {
        binding.etSearch.setOnKeyListener { _, keyCode, keyEvent ->
            if ((keyCode == KeyEvent.KEYCODE_ENTER) && (keyEvent.action == KeyEvent.ACTION_DOWN)) {
                hideKeyBoard()
                viewModel.searchUsersByQuery()
            }

            false
        }

        binding.ivSearch.setOnClickListener {
            hideKeyBoard()
            viewModel.searchUsersByQuery()
        }

        binding.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (binding.rvUsers.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val itemTotalCount = binding.rvUsers.adapter!!.itemCount

                if (itemTotalCount >= GithubSearchViewModel.PER_PAGE) {
                    if (lastVisibleItemPosition == itemTotalCount.minus(1)) {
                        viewModel.loadMoreData()
                    }
                }
            }
        })
    }

    private fun initCallback() {
        viewModel.notificationMsg.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { msg -> showToastMessage(msg) }
        })
    }

    private fun hideKeyBoard() {
        val imm = activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }

    companion object {
        fun newInstance() = GithubSearchFragment()
    }

}