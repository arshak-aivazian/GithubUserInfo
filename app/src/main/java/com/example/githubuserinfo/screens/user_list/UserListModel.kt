package com.example.githubuserinfo.screens.user_list

import com.example.githubuserinfo.screens.user_list.paging.UsersDataSourceFactory
import com.example.githubuserinfo.network.api.ApiService

class UserListModel(private val apiService: ApiService) {
    fun getSourceFactory(): UsersDataSourceFactory =
        UsersDataSourceFactory(apiService)
}