package com.example.netflixapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.netflixapp.data.model.NetflixTitle
import com.example.netflixapp.ui.viewmodel.NetflixViewModel

@Composable
fun AddTitleScreen(
    viewModel: NetflixViewModel,
    onBack: () -> Unit
) {
    // Variables d'estat per cada camp del formulari
    var showId by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var releaseYear by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var listedIn by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // Missatge del ViewModel (èxit o error)
    val message by viewModel.message.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Títol de la pantalla
        Text(
            text = "Afegir nou títol",
            style = MaterialTheme.typography.headlineMedium
        )

        // Camps del formulari
        OutlinedTextField(
            value = showId,
            onValueChange = { showId = it },
            label = { Text("Show ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Type (Movie o TV Show)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Títol") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = director,
            onValueChange = { director = it },
            label = { Text("Director") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = country,
            onValueChange = { country = it },
            label = { Text("País") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = releaseYear,
            onValueChange = { releaseYear = it },
            label = { Text("Any") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = rating,
            onValueChange = { rating = it },
            label = { Text("Rating") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Durada") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = listedIn,
            onValueChange = { listedIn = it },
            label = { Text("Gènere / listed_in") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripció") },
            modifier = Modifier.fillMaxWidth()
        )

        // Missatge d'èxit o error
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Botó per enviar el formulari
        Button(
            onClick = {
                val newTitle = NetflixTitle(
                    show_id = showId,
                    type = type,
                    title = title,
                    director = director,
                    country = country,
                    release_year = releaseYear.toIntOrNull() ?: 0,
                    rating = rating,
                    duration = duration,
                    listed_in = listedIn,
                    description = description
                )

                viewModel.insertTitle(newTitle)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar títol")
        }

        // Botó per tornar enrere
        TextButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tornar")
        }
    }
}