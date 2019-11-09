package com.example.musinsasampleapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musinsasampleapp.data.source.GithubRepository
import com.example.musinsasampleapp.data.vo.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubSearchViewModel(private val repository: GithubRepository) : ViewModel() {
    val query = MutableLiveData<String>("")
    private var currQuery: String? = null

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    private val _notificationMsg = MutableLiveData<Event<String>>()
    val notificationMsg: LiveData<Event<String>> get() = _notificationMsg

    private val _isProgress = MutableLiveData<Boolean>()
    val isProgress: LiveData<Boolean> get() = _isProgress

    private var pageNumber = 0
    private val localUserList = mutableListOf<User>()

    var myUserList = repository.loadMyUserList()

    fun searchUsersByQuery() {
        currQuery = query.value

        if (currQuery.isNullOrEmpty()) {
            showToastNotificationMsg("검색어를 입력해주세요.")
            return
        }

        pageNumber = 0

        repository
            .getUsersByQuery(currQuery!!, pageNumber, PER_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
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
                showToastNotificationMsg("네트워크 통신에 실패했습니다. ${it.message}")
            })
    }

    fun loadMoreData() {
        showProgressBar()
        pageNumber++

        repository
            .getUsersByQuery(currQuery!!, pageNumber, PER_PAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
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
                showToastNotificationMsg("네트워크 통신에 실패했습니다.")
                hideProgressBar()
            })
    }

    fun clearQuery() {
        query.value = ""
    }

    fun changeMyUserList(user: User) {
        when (user.checked) {
            true -> repository
                .insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { showToastNotificationMsg("데이터 삽입을 실패했습니다.") })

            false -> repository
                .deleteUserById(user.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { showToastNotificationMsg("데이터 삭제를 실패했습니다.") })
        }
    }

    private fun setUserChecked(user: User) {
        repository
            .findUserById(user.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userId ->
                user.checked = userId != null
            }, {
                showToastNotificationMsg("로컬 데이터를 가져오는데 실패했습니다.")
            })
    }

    private fun showToastNotificationMsg(msg: String) {
        _notificationMsg.value = Event(msg)
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