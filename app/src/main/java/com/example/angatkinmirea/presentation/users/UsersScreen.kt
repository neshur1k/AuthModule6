package com.example.angatkinmirea.presentation.users

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight

@Composable
fun UsersScreen(
    viewModel: UsersViewModel,
    onUserClick: (Int) -> Unit,
    onLogout: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    Column {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(state.error!!)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadUsers() }) {
                        Text("Повторить")
                    }
                }
            }

            else -> {

                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp)
                ) {
                    Text("Выйти")
                }

                LazyColumn {
                    items(state.users) { user ->
                        Card(
                            onClick = { onUserClick(user.id) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Row(modifier = Modifier.padding(16.dp)) {

                                AsyncImage(
                                    model = user.image,
                                    contentDescription = null,
                                    modifier = Modifier.size(64.dp)
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        text = "${user.firstName} ${user.lastName}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(user.email)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}