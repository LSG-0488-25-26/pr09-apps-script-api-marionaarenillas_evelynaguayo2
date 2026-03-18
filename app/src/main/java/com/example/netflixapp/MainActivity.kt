package com.example.netflixapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.netflixapp.ui.screen.HomeScreen
import com.example.netflixapp.ui.theme.NetflixAppTheme
import com.example.netflixapp.ui.viewmodel.NetflixViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NetflixAppTheme {
                val netflixViewModel: NetflixViewModel = viewModel()

                HomeScreen(
                    viewModel = netflixViewModel
                )
            }
        }
    }
}