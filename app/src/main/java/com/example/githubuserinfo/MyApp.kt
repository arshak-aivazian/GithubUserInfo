package com.example.githubuserinfo

import android.app.Application
import com.example.githubuserinfo.di.AppComponent
import com.example.githubuserinfo.di.AppModule
import com.example.githubuserinfo.di.DaggerAppComponent

class MyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: MyApp): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}