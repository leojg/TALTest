package com.example.sandbox.repository

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api")
    suspend fun getUsers(@Query("results") limit: Int = 30): Response<RandomUserResponse>

}