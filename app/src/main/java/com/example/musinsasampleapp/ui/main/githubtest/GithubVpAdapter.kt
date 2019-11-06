package com.example.musinsasampleapp.ui.main.githubtest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.musinsasampleapp.ui.main.githubtest.list.GithubListFragment
import com.example.musinsasampleapp.ui.main.githubtest.search.GithubSearchFragment

class GithubVpAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = listOf("검색", "내 목록")

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GithubSearchFragment.newInstance()
            else -> GithubListFragment.newInstance()
        }
    }

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = fragmentList[position]

}