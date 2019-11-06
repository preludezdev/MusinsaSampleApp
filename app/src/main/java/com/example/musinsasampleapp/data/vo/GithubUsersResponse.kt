package com.example.musinsasampleapp.data.vo


import com.google.gson.annotations.SerializedName

data class GithubUsersResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Item>
) {
    data class Item(
        @SerializedName("login")
        val login: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("score")
        val score: Double
    ) {
        fun convertItemIntoUser(checked: Boolean): User =
            User(login, id, avatarUrl, score, checked)
    }

}