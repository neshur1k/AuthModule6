package com.example.angatkinmirea.di

import android.content.Context
import com.example.angatkinmirea.data.datastore.TokenStorage
import com.example.angatkinmirea.data.remote.RetrofitInstance
import com.example.angatkinmirea.data.repository.UserRepositoryImpl
import com.example.angatkinmirea.domain.usecase.GetUserByIdUseCase
import com.example.angatkinmirea.domain.usecase.GetUsersUseCase
import com.example.angatkinmirea.domain.usecase.LoginUseCase
import com.example.angatkinmirea.domain.usecase.LogoutUseCase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first

class AppContainer(
    context: Context
) {

    private val tokenStorage =
        TokenStorage(context)

    private val api =
        RetrofitInstance.createApi {
            runBlocking {
                tokenStorage.token.first()
            }
        }

    private val repository =
        UserRepositoryImpl(
            api,
            tokenStorage
        )

    val loginUseCase =
        LoginUseCase(repository)

    val getUsersUseCase =
        GetUsersUseCase(repository)

    val getUserByIdUseCase =
        GetUserByIdUseCase(repository)

    val logoutUseCase =
        LogoutUseCase(repository)
}