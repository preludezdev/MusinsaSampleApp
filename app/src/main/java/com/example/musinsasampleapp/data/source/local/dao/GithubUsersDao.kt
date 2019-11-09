package com.example.musinsasampleapp.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musinsasampleapp.data.vo.User
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface GithubUsersDao {

    @Query("SELECT * from users_table ORDER BY login ASC")
    fun loadMyUserList(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User): Completable

    @Query("DELETE FROM users_table WHERE id = :userId")
    fun deleteUserById(userId: Int): Completable

    @Query("SELECT * FROM users_table WHERE id = :userId")
    fun findUserById(userId: Int): Maybe<User>
}