package com.example.sandbox.repository

import com.google.gson.annotations.SerializedName

data class LocationObject(@SerializedName("street") val street: HashMap<String, Any>)