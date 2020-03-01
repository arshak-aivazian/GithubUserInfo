package com.example.githubuserinfo.screens.user_list

import android.util.Log
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class UserListPresenter(private val model: UserListModel) : MvpPresenter<UserListView>() {

    companion object{
        val pageSize = 30
        val tag = "MyTag"
    }

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        disposable.add(
            createPagedBuilder()
        )
    }

    fun onItemClicked(login: String) {
        viewState.openUserDetail(login)
    }

    private fun createPagedBuilder(): Disposable {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setEnablePlaceholders(false)
            .build()

        return RxPagedListBuilder(model.getSourceFactory(), config)
            .setFetchScheduler(Schedulers.io())
            .setNotifyScheduler(AndroidSchedulers.mainThread())
            .buildObservable()
            .subscribe(
                { viewState.setUserList(it) },
                { e ->
                    Log.i(tag, e.localizedMessage)
                    viewState.showErrorMessage()
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}