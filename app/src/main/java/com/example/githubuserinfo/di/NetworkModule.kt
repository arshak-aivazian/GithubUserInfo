package com.example.githubuserinfo.di

import com.example.githubuserinfo.network.api.ApiService
import com.example.githubuserinfo.screens.user_detail.UserDetailModel
import com.example.githubuserinfo.screens.user_list.UserListModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule  {
    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiService.create()

    @Provides
    fun provideUserListModel(apiService: ApiService): UserListModel = UserListModel(apiService)

    @Provides
    fun provideUserDetailModel(apiService: ApiService): UserDetailModel = UserDetailModel(apiService)
}