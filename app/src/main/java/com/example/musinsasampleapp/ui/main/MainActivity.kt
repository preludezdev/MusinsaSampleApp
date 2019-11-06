package com.example.musinsasampleapp.ui.main

import android.content.Intent
import android.os.Bundle
import com.example.musinsasampleapp.R
import com.example.musinsasampleapp.base.BaseActivity
import com.example.musinsasampleapp.databinding.ActivityMainBinding
import com.example.musinsasampleapp.ui.main.githubtest.GithubActivity
import com.example.musinsasampleapp.ui.main.uitest.UiActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEvent()
    }

    private fun initEvent() {
        binding.btGithub.setOnClickListener {
            startActivity(Intent(this, GithubActivity::class.java))
        }

        binding.btUi.setOnClickListener {
            startActivity(Intent(this, UiActivity::class.java))
        }
    }

}