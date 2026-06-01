package com.example.angatkinmirea.domain.repository

import com.example.angatkinmirea.domain.model.User

interface UserRepository {

    suspend fun login(
        username: String,
        password: String
    )

    suspend fun getUsers(): List<User>

    suspend fun getUserById(id: Int): User

    suspend fun logout()

    suspend fun getToken(): String?
}