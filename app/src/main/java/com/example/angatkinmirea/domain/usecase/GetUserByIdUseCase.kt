package com.example.angatkinmirea.domain.usecase

import com.example.angatkinmirea.domain.repository.UserRepository

class GetUserByIdUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        id: Int
    ) = repository.getUserById(id)
}