package com.example.angatkinmirea.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.angatkinmirea.domain.usecase.GetUsersUseCase

class UsersViewModelFactory(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return UsersViewModel(
            getUsersUseCase
        ) as T
    }
}