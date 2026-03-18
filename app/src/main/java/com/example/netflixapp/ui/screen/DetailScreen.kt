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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.netflixapp.ui.viewmodel.NetflixViewModel

@Composable
fun DetailScreen(
    viewModel: NetflixViewModel,
    showId: String,
    onBack: () -> Unit
) {
    // Observem el títol seleccionat i el missatge del ViewModel
    val selectedTitle by viewModel.selectedTitle.observeAsState()
    val message by viewModel.message.observeAsState("")

    // Quan entrem a la pantalla, carreguem el detall per id
    LaunchedEffect(showId) {
        viewModel.loadTitleById(showId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Detall del títol",
            style = MaterialTheme.typography.headlineMedium
        )

        // Si hi ha missatge d'error, el mostrem
        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error
            )
        }

        // Si hi ha dades carregades, les mostrem
        selectedTitle?.let { title ->
            Text(text = "ID: ${title.show_id}")
            Text(text = "Títol: ${title.title}")
            Text(text = "Tipus: ${title.type}")
            Text(text = "Director: ${title.director}")
            Text(text = "País: ${title.country}")
            Text(text = "Any: ${title.release_year}")
            Text(text = "Rating: ${title.rating}")
            Text(text = "Durada: ${title.duration}")
            Text(text = "Gènere: ${title.listed_in}")
            Text(text = "Descripció: ${title.description}")
        }

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tornar")
        }
    }
}