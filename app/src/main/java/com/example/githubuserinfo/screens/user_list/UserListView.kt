package com.example.githubuserinfo.screens.user_list

import androidx.paging.PagedList
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.githubuserinfo.network.pojo.User

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserListView: MvpView {
    fun setUserList(pagedList: PagedList<User>)
    fun showErrorMessage()
    fun openUserDetail(login: String)
}