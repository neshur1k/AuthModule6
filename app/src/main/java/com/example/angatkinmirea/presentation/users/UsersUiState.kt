package com.example.angatkinmirea.presentation.users

import com.example.angatkinmirea.domain.model.User

data class UsersUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null
)