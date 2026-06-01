package com.example.angatkinmirea.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.angatkinmirea.di.AppContainer
import com.example.angatkinmirea.presentation.login.LoginScreen
import com.example.angatkinmirea.presentation.login.LoginViewModel
import com.example.angatkinmirea.presentation.login.LoginViewModelFactory
import com.example.angatkinmirea.presentation.users.*
import com.example.angatkinmirea.presentation.detail.*

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current

    val container = remember {
        AppContainer(context)
    }

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        composable(Routes.LOGIN) {

            val vm: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(container.loginUseCase)
            )

            LoginScreen(
                viewModel = vm,
                onLoginSuccess = {
                    navController.navigate(Routes.USERS) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.USERS) {

            val vm: UsersViewModel = viewModel(
                factory = UsersViewModelFactory(container.getUsersUseCase)
            )

            UsersScreen(
                viewModel = vm,
                onUserClick = { id ->
                    navController.navigate("${Routes.DETAIL}/$id")
                }
            )
        }

        composable(
            route = "${Routes.DETAIL}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: 0

            val vm: UserDetailViewModel = viewModel(
                factory = UserDetailViewModelFactory(
                    container.getUserByIdUseCase
                )
            )

            UserDetailScreen(
                userId = id,
                viewModel = vm,
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}