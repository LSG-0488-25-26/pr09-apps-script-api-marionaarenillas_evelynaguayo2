package com.example.netflixapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import com.example.netflixapp.ui.viewmodel.NetflixViewModel

@Composable
fun HomeScreen(
    viewModel: NetflixViewModel,
    onItemClick: (String) -> Unit = {},
    onGoToAddTitle: () -> Unit = {}
) {
    val titles by viewModel.titles.observeAsState(emptyList())
    val message by viewModel.message.observeAsState("")

    LaunchedEffect(Unit) {
        viewModel.loadAllTitles()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Catàleg Netflix",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = onGoToAddTitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text("Afegir nou títol")
        }

        if (message.isNotEmpty()) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(titles) { title ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            onItemClick(title.show_id)
                        }
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = title.title,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(text = "Tipus: ${title.type}")
                        Text(text = "Any: ${title.release_year}")
                        Text(text = "Rating: ${title.rating}")
                    }
                }
            }
        }
    }
}