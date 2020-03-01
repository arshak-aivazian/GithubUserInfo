package com.example.githubuserinfo.screens.user_detail

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class UserDetailPresenter(private val model: UserDetailModel, private val login: String) :
    MvpPresenter<UserDetailView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposable.add(
            loadUserInfo(login)
        )
    }

    fun onBtnOpenClicked(url: String) {
        viewState.openUrl(url)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    private fun loadUserInfo(login: String): Disposable {
        return model.getUserInfo(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.setUserInfo(it)
                },
                {
                    Log.i(tag, it.localizedMessage)
                    viewState.showError()
                }
            )
    }


    companion object{
        val tag = "MyTag"
    }
}