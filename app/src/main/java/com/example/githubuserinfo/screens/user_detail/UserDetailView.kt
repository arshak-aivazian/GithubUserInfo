package com.example.githubuserinfo.screens.user_detail

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.githubuserinfo.network.pojo.User

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailView : MvpView {
    fun setUserInfo(user: User)
    fun openUrl(url: String)
    fun showError()
}