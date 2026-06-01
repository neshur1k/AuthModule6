package com.example.angatkinmirea.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun UserDetailScreen(
    userId: Int,
    viewModel: UserDetailViewModel,
    onLogout: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(userId) {
        viewModel.loadUser(userId)
    }

    when {

        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Text(state.error!!)
        }

        state.user != null -> {
            val user = state.user!!

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                AsyncImage(
                    model = user.image,
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )

                Text("${user.firstName} ${user.lastName}")
                Text(user.email)

                Button(onClick = onLogout) {
                    Text("Выйти")
                }
            }
        }
    }
}