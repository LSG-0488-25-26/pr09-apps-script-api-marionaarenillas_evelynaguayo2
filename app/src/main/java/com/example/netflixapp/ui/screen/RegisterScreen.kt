package com.example.netflixapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.netflixapp.ui.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    onGoToLogin: () -> Unit
) {
    // Variables d'estat per guardar el text escrit
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Observem missatge del ViewModel
    val authMessage by authViewModel.authMessage.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Títol de la pantalla
        Text(
            text = "Registre",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Camp d'usuari
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuari") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        // Camp de contrasenya
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contrasenya") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        // Missatge d'error o confirmació
        if (authMessage.isNotEmpty()) {
            Text(
                text = authMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        // Botó per registrar usuari
        Button(
            onClick = {
                authViewModel.register(username, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("Registrar")
        }

        // Botó per tornar al login
        TextButton(
            onClick = onGoToLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ja tens compte? Ves al login")
        }
    }
}