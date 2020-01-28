package com.example.sandbox.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val HTTP_TIMEOUT = 60L
const val BASE_URL = "https://randomuser.me/"

val defaultHttpClient: OkHttpClient = OkHttpClient().newBuilder()
    .connectTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
    .build()

inline fun <reified T : Any>getRetrofitService(): T {
    val retrofit = Retrofit.Builder()
        .client(defaultHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}