package com.example.musinsasampleapp.ui.main.githubtest.search

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseFragment
import com.example.musinsasampleapp.data.vo.User
import com.example.musinsasampleapp.databinding.FragmentGithubSearchBinding
import com.example.musinsasampleapp.vm.GithubSearchViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GithubSearchFragment :
    BaseFragment<FragmentGithubSearchBinding>(R.layout.fragment_github_search) {

    private val viewModel by sharedViewModel<GithubSearchViewModel>()
    private val rvAdapter by lazy {
        GithubSearchAdapter(diffUtilCallback) { position, action ->
            clickEventCallback(
                position,
                action
            )
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
    }

    private fun initCallback() {
        viewModel.notificationMsg.observe(this, Observer {
            showToastMessage(it)
        })

        viewModel.query.observe(this, Observer {
            if (it.isEmpty()) {
                binding.ivCancel.visibility = View.INVISIBLE
            } else {
                binding.ivCancel.visibility = View.VISIBLE
            }
        })

        viewModel.userList.observe(this, Observer {
            rvAdapter.submitList(it)
        })
    }

    private fun hideKeyBoard() {
        val imm = activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }

    companion object {
        val diffUtilCallback =
            object : DiffUtil.ItemCallback<User>() {
                override fun areItemsTheSame(
                    oldItem: User,
                    newItem: User
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: User,
                    newItem: User
                ): Boolean {
                    return oldItem.login == newItem.login
                }
            }

        fun newInstance() =
            GithubSearchFragment()
    }

}