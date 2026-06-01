package com.example.angatkinmirea.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.angatkinmirea.domain.usecase.GetUserByIdUseCase

class UserDetailViewModelFactory(
    private val useCase: GetUserByIdUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserDetailViewModel(useCase) as T
    }
}