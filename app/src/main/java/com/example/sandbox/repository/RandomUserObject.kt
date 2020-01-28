package com.example.sandbox.repository

import com.google.gson.annotations.SerializedName

data class RandomUserObject(
    @SerializedName("gender") val id: String,
    @SerializedName("name") val name: HashMap<String, String>,
    @SerializedName("picture") val picture: HashMap<String, String>,
    @SerializedName("dob") val dob: HashMap<String, String>,
    @SerializedName("email") val email: String,
    @SerializedName("login") val login: HashMap<String, String>,
    @SerializedName("location") val location: LocationObject,
    @SerializedName("phone") val phone: String
)