package com.example.musinsasampleapp.data.source.remote

import com.example.musinsasampleapp.data.vo.GithubUsersResponse
import com.example.musinsasampleapp.network.GithubApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRemoteDataSourceImpl(private val api: GithubApiService) : GithubRemoteDataSource {
    override fun getUsersByQuery(
        query: String?,
        page: Int,
        per_page: Int,
        onSuccess: (data: GithubUsersResponse) -> Unit,
        onFail: (errorMsg: String) -> Unit
    ) {
        if (query.isNullOrEmpty()) {
            onFail("네트워크 통신에 실패했습니다.(query = null)")
            return
        }

        api.searchUserByQuery(query, page, per_page)
            .enqueue(object : Callback<GithubUsersResponse> {
                override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                    onFail("네트워크 통신에 실패했습니다.")
                }

                override fun onResponse(
                    call: Call<GithubUsersResponse>,
                    response: Response<GithubUsersResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()

                        if (data != null) {
                            onSuccess(data)
                        }
                    } else {
                        onFail("네트워크 통신에 실패했습니다.")
                    }
                }
            })
    }

}