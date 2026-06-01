package com.example.angatkinmirea
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.angatkinmirea.di.AppContainer
import com.example.angatkinmirea.navigation.AppNavGraph
import com.example.angatkinmirea.presentation.login.LoginScreen
import com.example.angatkinmirea.presentation.login.LoginViewModel
import com.example.angatkinmirea.presentation.login.LoginViewModelFactory
import com.example.angatkinmirea.presentation.ui.theme.AngatkinMIREATheme
import com.example.angatkinmirea.presentation.users.UsersScreen
import com.example.angatkinmirea.presentation.users.UsersViewModel
import com.example.angatkinmirea.presentation.users.UsersViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val container = AppContainer(applicationContext)

        setContent {
            AngatkinMIREATheme {
                AppNavGraph()
            }
        }
    }
}