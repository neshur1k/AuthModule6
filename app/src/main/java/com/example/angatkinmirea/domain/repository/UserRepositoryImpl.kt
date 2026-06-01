package com.example.angatkinmirea.data.repository

import com.example.angatkinmirea.data.datastore.TokenStorage
import com.example.angatkinmirea.data.remote.ApiService
import com.example.angatkinmirea.data.remote.LoginRequest
import com.example.angatkinmirea.domain.model.User
import com.example.angatkinmirea.domain.repository.UserRepository
import kotlinx.coroutines.flow.first

class UserRepositoryImpl(
    private val api: ApiService,
    private val tokenStorage: TokenStorage
) : UserRepository {

    override suspend fun login(
        username: String,
        password: String
    ) {
        val response = api.login(
            LoginRequest(
                username,
                password
            )
        )

        tokenStorage.saveToken(
            response.accessToken
        )
    }

    override suspend fun getUsers(): List<User> {

        return api.getUsers().users.map {
            User(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                username = it.username,
                email = it.email,
                image = it.image
            )
        }
    }

    override suspend fun getUserById(
        id: Int
    ): User {

        val user = api.getUserById(id)

        return User(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            username = user.username,
            email = user.email,
            image = user.image
        )
    }

    override suspend fun logout() {
        tokenStorage.clearToken()
    }

    override suspend fun getToken(): String? {
        return tokenStorage.token.first()
    }
}