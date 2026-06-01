package com.example.angatkinmirea.data.remote

data class LoginResponse(
    val accessToken: String,
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: String
)