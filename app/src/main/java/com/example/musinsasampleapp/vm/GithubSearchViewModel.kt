package com.example.musinsasampleapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musinsasampleapp.data.source.GithubRepository
import com.example.musinsasampleapp.data.vo.User

class GithubSearchViewModel(private val repository: GithubRepository) : ViewModel() {
    val query = MutableLiveData<String>("")
    private var currQuery: String? = null

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    private val _notificationMsg = MutableLiveData<String>()
    val notificationMsg: LiveData<String> get() = _notificationMsg

    private val _isProgress = MutableLiveData<Boolean>()
    val isProgress: LiveData<Boolean> get() = _isProgress

    private var pageNumber = 0
    private val localUserList = mutableListOf<User>()

    lateinit var myUserList: LiveData<List<User>>

    init {
        Thread(Runnable {
            myUserList = repository.loadMyUserList()
        }).start()
    }

    fun searchUsersByQuery() {
        currQuery = query.value

        if (currQuery.isNullOrEmpty()) {
            showToastNotificationMsg("검색어를 입력해주세요.")
            return
        }

        pageNumber = 0

        repository
            .getUsersByQuery(currQuery, pageNumber, PER_PAGE, { response ->
                val newUserList = response.items
                if (newUserList.isEmpty()) {
                    showToastNotificationMsg("검색 결과가 없습니다.")
                } else {
                    localUserList.clear()
                    localUserList.addAll(
                        response.items.map { item ->
                            val user = item.convertItemIntoUser(false)
                            setUserChecked(user)
                            user
                        })
                    _userList.value = localUserList
                }
            }, {
                showToastNotificationMsg(it)
            })
    }

    fun loadMoreData() {
        showProgressBar()
        pageNumber++

        repository
            .getUsersByQuery(currQuery, pageNumber, PER_PAGE, { response ->
                val newUserList = response.items
                if (newUserList.isEmpty()) {
                    showToastNotificationMsg("검색 결과가 더 없습니다.")
                } else {
                    localUserList.addAll(
                        response.items.map { item ->
                            val user = item.convertItemIntoUser(false)
                            setUserChecked(user)
                            user
                        })
                    _userList.value = localUserList
                }
                hideProgressBar()
            }, {
                showToastNotificationMsg(it)
                hideProgressBar()
            })
    }

    fun clearQuery() {
        query.value = ""
    }

    fun changeMyUserList(user: User) {
        Thread(Runnable {
            when (user.checked) {
                true -> repository.insertUser(user)
                false -> repository.deleteUserById(user.id)
            }
        }).start()
    }

    private fun setUserChecked(user: User) {
        Thread(Runnable {
            user.checked = repository.findUserById(user.id) != null
        }).start()
    }

    private fun showToastNotificationMsg(msg: String) {
        _notificationMsg.value = msg
    }

    private fun showProgressBar() {
        _isProgress.value = true
    }

    private fun hideProgressBar() {
        _isProgress.value = false
    }

    companion object {
        const val PER_PAGE = 30
    }

}