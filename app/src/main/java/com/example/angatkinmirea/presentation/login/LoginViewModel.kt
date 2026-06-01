package com.example.angatkinmirea.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.angatkinmirea.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        LoginUiState()
    )

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    fun updateUsername(value: String) {
        _uiState.value =
            _uiState.value.copy(
                username = value
            )
    }

    fun updatePassword(value: String) {
        _uiState.value =
            _uiState.value.copy(
                password = value
            )
    }

    fun login() {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    error = null
                )

            try {

                loginUseCase(
                    _uiState.value.username,
                    _uiState.value.password
                )

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        isSuccess = true
                    )

            } catch (e: Exception) {

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        error = e.message ?: "Ошибка"
                    )
            }
        }
    }
}