package com.example.sandbox.domain

import java.io.Serializable

data class Employee(
    val name: String,
    val picture: String?,
    val dob: String?,
    val email: String?,
    val phone: String?,
    val address: String?,
    val username: String?,
    val password: String?
): Serializable