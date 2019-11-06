package com.example.musinsasampleapp.ui.main.githubtest

import android.os.Bundle
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseActivity
import com.example.musinsasampleapp.databinding.ActivityGithubBinding
import com.example.musinsasampleapp.vm.GithubSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubActivity : BaseActivity<ActivityGithubBinding>(R.layout.activity_github) {

    private val viewModel by viewModel<GithubSearchViewModel>()
    private val githubAdapter by lazy { GithubVpAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {
            viewPager.adapter = githubAdapter
            viewPager.offscreenPageLimit = githubAdapter.count
            tabLayout.setupWithViewPager(viewPager)
        }
    }
}
