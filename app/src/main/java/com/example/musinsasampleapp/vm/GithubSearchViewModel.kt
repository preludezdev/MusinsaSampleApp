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

    private val _myUserList = MutableLiveData<List<User>>()
    val myUserList: LiveData<List<User>> get() = _myUserList

    private val _notificationMsg = MutableLiveData<String>()
    val notificationMsg: LiveData<String> get() = _notificationMsg

    private val localMyUserList = HashMap<Int, User>()

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
                        response.items.map { item -> item.convertItemIntoUser(isUserChecked(item.id)) }
                }
            }, {
                showToastNotificationMsg(it)
            })
    }

    fun clearQuery() {
        query.value = ""
    }

    fun changeMyUserList(user: User) {
        when (user.checked) {
            true -> localMyUserList[user.id] = user
            false -> localMyUserList.remove(user.id)
        }

        _myUserList.value = ArrayList(localMyUserList.values)
    }

    private fun isUserChecked(UserId: Int): Boolean = localMyUserList.containsKey(UserId)

    private fun showToastNotificationMsg(msg: String) {
        _notificationMsg.value = msg
    }

}