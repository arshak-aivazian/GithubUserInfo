package com.example.githubuserinfo.di

import com.example.githubuserinfo.screens.user_detail.UserDetailActivity
import com.example.githubuserinfo.screens.user_list.UserListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(activity: UserListActivity)
    fun inject(activity: UserDetailActivity)
}