package com.example.angatkinmirea.domain.usecase

import com.example.angatkinmirea.domain.repository.UserRepository

class LogoutUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke() {
        repository.logout()
    }
}