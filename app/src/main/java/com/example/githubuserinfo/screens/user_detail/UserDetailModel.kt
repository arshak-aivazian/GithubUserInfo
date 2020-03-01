package com.example.githubuserinfo.screens.user_detail

import com.example.githubuserinfo.network.api.ApiService
import com.example.githubuserinfo.network.pojo.User
import io.reactivex.Single

class UserDetailModel(private val apiService: ApiService) {
    fun getUserInfo(login: String): Single<User> {
        return apiService.getUserByLogin(login)
    }
}