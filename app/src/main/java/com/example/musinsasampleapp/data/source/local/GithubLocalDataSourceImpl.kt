package com.example.musinsasampleapp.data.source.local

import androidx.lifecycle.LiveData
import com.example.musinsasampleapp.data.source.local.dao.GithubUsersDao
import com.example.musinsasampleapp.data.vo.User

class GithubLocalDataSourceImpl(private val dao: GithubUsersDao) : GithubLocalDataSource {

    override fun loadMyUserList(): LiveData<List<User>> = dao.loadMyUserList()

    override fun insertUser(user: User) = dao.insertUser(user)

    override fun deleteUserById(userId: Int) = dao.deleteUserById(userId)

    override fun findUserById(userId: Int): User = dao.findUserById(userId)

}