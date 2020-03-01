package com.example.githubuserinfo

import com.example.githubuserinfo.network.api.ApiService
import com.example.githubuserinfo.network.pojo.User
import com.example.githubuserinfo.screens.user_detail.UserDetailModel
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoadUserTest {

    @Mock
    lateinit var apiService: ApiService

    lateinit var userDetailModel: UserDetailModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        userDetailModel = UserDetailModel(apiService)

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `get user by login`() {

        val userExpected =
            User(login = "arshak-aivazian", html_url = "https://github.com/arshak-aivazian/")

        `when`(apiService.getUserByLogin(userExpected.login)).thenReturn(
            Single.just(userExpected)
        )
        val exampleUser = userDetailModel
            .getUserInfo(userExpected.login)
            .blockingGet()
        assert(userExpected.login.equals(exampleUser.login))
    }
}