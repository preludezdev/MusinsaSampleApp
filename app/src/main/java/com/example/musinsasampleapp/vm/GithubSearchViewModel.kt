package com.example.musinsasampleapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musinsasampleapp.data.source.GithubRepository
import com.example.musinsasampleapp.data.vo.User

class GithubSearchViewModel(private val repository: GithubRepository) : ViewModel() {
    val query = MutableLiveData<String>("")

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    private val _notificationMsg = MutableLiveData<String>()
    val notificationMsg: LiveData<String> get() = _notificationMsg

    lateinit var myUserList: LiveData<List<User>>

    init {
        Thread(Runnable {
            myUserList = repository.loadMyUserList()
        }).start()
    }

    fun searchUsersByQuery() {
        val currQuery = query.value

        if (currQuery.isNullOrEmpty()) {
            showToastNotificationMsg("검색어를 입력해주세요")
            return
        }

        repository
            .getUsersByQuery(currQuery, { response ->
                val newUserList = response.items
                if (newUserList.isEmpty()) {
                    showToastNotificationMsg("검색 결과가 없습니다.")
                } else {
                    _userList.value =
                        response.items.map { item ->
                            val user = item.convertItemIntoUser(false)
                            setUserChecked(user)
                            user
                        }
                }
            }, {
                showToastNotificationMsg(it)
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

}