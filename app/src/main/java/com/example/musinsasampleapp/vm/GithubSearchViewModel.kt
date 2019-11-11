package com.example.musinsasampleapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musinsasampleapp.data.source.GithubRepository
import com.example.musinsasampleapp.data.vo.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GithubSearchViewModel(private val repository: GithubRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val query = MutableLiveData<String>("")
    private var currQuery: String? = null

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    private val _notificationMsg = MutableLiveData<Event<String>>()
    val notificationMsg: LiveData<Event<String>> get() = _notificationMsg

    private val _isProgress = MutableLiveData<Boolean>()
    val isProgress: LiveData<Boolean> get() = _isProgress

    private var pageNumber = FIRST_PAGE
    private val localUserList = mutableListOf<User>()

    var myUserList = repository.loadMyUserList()

    fun searchUsersByQuery() {
        currQuery = query.value

        if (currQuery.isNullOrEmpty()) {
            showToastNotificationMsg("검색어를 입력해주세요.")
            return
        }

        showProgressBar()
        pageNumber = FIRST_PAGE

        compositeDisposable.add(
            repository
                .getUsersByQuery(currQuery!!, pageNumber, PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { response ->
                    val newUserList =
                        response.items.map {
                            val user = it.convertItemIntoUser(false)
                            setUserChecked(user)
                            user
                        }

                    Single.just(newUserList)
                }
                .subscribe({ newUserList ->
                    if (newUserList.isEmpty()) {
                        showToastNotificationMsg("검색 결과가 없습니다.")
                    } else {
                        localUserList.clear()
                        localUserList.addAll(newUserList)
                        _userList.value = localUserList
                    }
                    hideProgressBar()
                }, {
                    showToastNotificationMsg("네트워크 통신에 실패했습니다. ${it.message}")
                    hideProgressBar()
                })
        )
    }

    fun loadMoreData() {
        showProgressBar()
        pageNumber++

        compositeDisposable.add(
            repository
                .getUsersByQuery(currQuery!!, pageNumber, PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { response ->
                    val newUserList =
                        response.items.map {
                            val user = it.convertItemIntoUser(false)
                            setUserChecked(user)
                            user
                        }

                    Single.just(newUserList)
                }
                .subscribe({ newUserList ->
                    if (newUserList.isEmpty()) {
                        showToastNotificationMsg("검색 결과가 없습니다.")
                    } else {
                        localUserList.addAll(newUserList)
                        _userList.value = localUserList
                    }
                    hideProgressBar()
                }, {
                    showToastNotificationMsg("네트워크 통신에 실패했습니다.")
                    hideProgressBar()
                })
        )
    }

    private fun setUserChecked(user: User) {
        compositeDisposable.add(
            repository.findUserById(user.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user.checked = true
                }, {
                    showToastNotificationMsg("로컬 데이터를 가져오는데 실패했습니다.")
                }, {
                    user.checked = false
                })
        )
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

    private fun showToastNotificationMsg(msg: String) {
        _notificationMsg.value = Event(msg)
    }

    private fun showProgressBar() {
        _isProgress.value = true
    }

    private fun hideProgressBar() {
        _isProgress.value = false
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    companion object {
        const val FIRST_PAGE = 1
        const val PER_PAGE = 20
    }

}