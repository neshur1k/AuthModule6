package com.example.angatkinmirea.domain.usecase

import com.example.angatkinmirea.domain.repository.UserRepository

class GetUsersUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke() =
        repository.getUsers()
}