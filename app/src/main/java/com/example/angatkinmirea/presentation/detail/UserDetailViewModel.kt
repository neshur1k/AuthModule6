package com.example.angatkinmirea.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.angatkinmirea.domain.model.User
import com.example.angatkinmirea.domain.usecase.GetUserByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UserDetailUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)

class UserDetailViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserDetailUiState())
    val state: StateFlow<UserDetailUiState> = _state

    fun loadUser(id: Int) {
        viewModelScope.launch {

            _state.value = UserDetailUiState(isLoading = true)

            try {
                val user = getUserByIdUseCase(id)

                _state.value = UserDetailUiState(
                    user = user
                )

            } catch (e: Exception) {
                _state.value = UserDetailUiState(
                    error = e.message ?: "Ошибка"
                )
            }
        }
    }
}