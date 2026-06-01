package com.example.angatkinmirea
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.angatkinmirea.di.AppContainer
import com.example.angatkinmirea.presentation.login.LoginScreen
import com.example.angatkinmirea.presentation.login.LoginViewModel
import com.example.angatkinmirea.presentation.login.LoginViewModelFactory
import com.example.angatkinmirea.presentation.ui.theme.AngatkinMIREATheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val container = AppContainer(applicationContext)

        setContent {
            AngatkinMIREATheme {
                val vm: LoginViewModel =
                    viewModel(
                        factory =
                        LoginViewModelFactory(
                            container.loginUseCase
                        )
                    )

                LoginScreen(
                    viewModel = vm,
                    onLoginSuccess = {}
                )
            }
        }
    }
}