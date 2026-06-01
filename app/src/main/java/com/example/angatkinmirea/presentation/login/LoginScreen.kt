package com.example.angatkinmirea.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    if (state.isSuccess) {
        onLoginSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment =
        Alignment.CenterHorizontally,

        verticalArrangement =
        Arrangement.Center
    ) {

        OutlinedTextField(
            value = state.username,
            onValueChange = {
                viewModel.updateUsername(it)
            },
            label = {
                Text("Username")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = {
                viewModel.updatePassword(it)
            },
            label = {
                Text("Password")
            },
            modifier = Modifier.fillMaxWidth()
        )

        state.error?.let {

            Text(
                text = it,
                modifier = Modifier.padding(8.dp)
            )
        }

        if (state.isLoading) {

            CircularProgressIndicator()

        } else {

            Button(
                onClick = {
                    viewModel.login()
                }
            ) {
                Text("Войти")
            }
        }
    }
}