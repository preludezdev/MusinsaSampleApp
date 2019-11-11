package com.example.musinsasampleapp.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.musinsasampleapp.data.source.local.GithubLocalDataSource
import com.example.musinsasampleapp.data.source.remote.GithubRemoteDataSource
import com.example.musinsasampleapp.data.vo.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubRepositoryImpl(
    private val remoteDataSource: GithubRemoteDataSource,
    private val localDataSource: GithubLocalDataSource
) : GithubRepository {

    //remote
    override fun getUsersByQuery(query: String, page: Int, per_page: Int): Single<List<User>> =
        remoteDataSource.getUsersByQuery(query, page, per_page)
            .flatMap { response ->
                val newUserList =
                    response.items.map {
                        val user = it.convertItemIntoUser(false)
                        setUserChecked(user)
                        user
                    }

                Single.just(newUserList)
            }

    private fun setUserChecked(user: User) {
        findUserById(user.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                user.checked = true
            }, {
                Log.d("test", "로컬 데이터를 가져오는데 실패했습니다.")
            }, {
                user.checked = false
            })
    }

    //local
    override fun loadMyUserList(): LiveData<List<User>> = localDataSource.loadMyUserList()

    override fun insertUser(user: User) = localDataSource.insertUser(user)

    override fun deleteUserById(userId: Int) = localDataSource.deleteUserById(userId)

    override fun findUserById(userId: Int) = localDataSource.findUserById(userId)

}