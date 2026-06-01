package com.example.angatkinmirea.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.angatkinmirea.di.AppContainer
import com.example.angatkinmirea.presentation.login.*
import com.example.angatkinmirea.presentation.users.*
import com.example.angatkinmirea.presentation.detail.*

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current

    // ✅ SINGLETON container (stable)
    val container = remember { AppContainer(context) }

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

            // ❌ УБРАЛИ users_graph (его нет)
            val vm: UsersViewModel = viewModel(
                factory = UsersViewModelFactory(container.getUsersUseCase)
            )

            UsersScreen(
                viewModel = vm,
                onUserClick = { id ->
                    navController.navigate("${Routes.DETAIL}/$id")
                },
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.USERS) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = "${Routes.DETAIL}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
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
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}