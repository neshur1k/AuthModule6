package com.example.angatkinmirea.domain.usecase

import com.example.angatkinmirea.domain.repository.UserRepository

class LoginUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String
    ) {
        repository.login(
            username,
            password
        )
    }
}