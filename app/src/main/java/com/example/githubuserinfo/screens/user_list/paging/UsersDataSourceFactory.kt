package com.example.githubuserinfo.screens.user_list.paging

import androidx.paging.DataSource
import com.example.githubuserinfo.network.api.ApiService
import com.example.githubuserinfo.network.pojo.User

class UsersDataSourceFactory(
    private val apiSevice: ApiService
) : DataSource.Factory<Int, User>() {

    override fun create(): DataSource<Int, User> {
        return UsersDataSource(apiSevice)
    }
}