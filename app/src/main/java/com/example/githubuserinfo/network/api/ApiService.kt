package com.example.githubuserinfo.network.api

import com.example.githubuserinfo.network.pojo.User
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("users?")
    fun getUsers(
        @Query("since") offset: Int = 0
    ): Flowable<List<User>>

    @GET("users/{name}")
    fun getUserByLogin(
        @Path("name") name: String
    ): Single<User>

    companion object Factory {
        fun create(): ApiService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()


            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://api.github.com/")
                .build()

            return retrofit.create(ApiService::class.java);
        }
    }
}