package com.example.sandbox.repository

import com.google.gson.annotations.SerializedName

data class RandomUserResponse(
    @SerializedName("results") val results: List<RandomUserObject>
)