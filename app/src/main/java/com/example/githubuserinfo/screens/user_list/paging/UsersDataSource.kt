package com.example.githubuserinfo.screens.user_list.paging

import androidx.paging.PageKeyedDataSource
import com.example.githubuserinfo.network.api.ApiService
import com.example.githubuserinfo.network.pojo.User

class UsersDataSource(
    private val apiService: ApiService
) : PageKeyedDataSource<Int, User>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        apiService.getUsers()
            .subscribe { response ->
                callback.onResult(response, null, response.last().id)
            }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, User>
    ) {
        apiService.getUsers(params.key)
            .subscribe { response ->
                callback.onResult(response, response.last().id)
            }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
    }

}