package com.example.sandbox.repository

class RandomUserRepository(val randomUserApi: RandomUserApi) {

    suspend fun getEmployees(size: Int = 30): List<RandomUserObject>? {
        val result = randomUserApi.getUsers(size)
        return if (result.isSuccessful && result.body() != null) {
            result.body()!!.results
        } else {
            null
        }

    }

}