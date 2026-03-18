package com.example.netflixapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.netflixapp.ui.screen.HomeScreen
import com.example.netflixapp.ui.screen.LoginScreen
import com.example.netflixapp.ui.screen.RegisterScreen
import com.example.netflixapp.ui.theme.NetflixAppTheme
import com.example.netflixapp.ui.viewmodel.AuthViewModel
import com.example.netflixapp.ui.viewmodel.NetflixViewModel
import com.example.netflixapp.ui.screen.AddTitleScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NetflixAppTheme {

                // Controlador de navegació de l'app
                val navController = rememberNavController()

                // ViewModel de login/registre
                val authViewModel: AuthViewModel = viewModel()

                // ViewModel de dades Netflix
                val netflixViewModel: NetflixViewModel = viewModel()

                // Definim totes les pantalles disponibles
                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    // Pantalla de login
                    composable("login") {
                        LoginScreen(
                            authViewModel = authViewModel,

                            // Si el login va bé, anem a home
                            onLoginSuccess = {
                                navController.navigate("home") {
                                    // Elimina login de la pila per no tornar enrere amb el botó back
                                    popUpTo("login") { inclusive = true }
                                }
                            },

                            // Navegar a registre
                            onGoToRegister = {
                                navController.navigate("register")
                            }
                        )
                    }

                    // Pantalla de registre
                    composable("register") {
                        RegisterScreen(
                            authViewModel = authViewModel,

                            // Tornar a login
                            onGoToLogin = {
                                navController.popBackStack()
                            }
                        )
                    }

                    // Pantalla principal amb la llista
                    composable("home") {
                        HomeScreen(
                            viewModel = netflixViewModel,
                            onGoToAddTitle = {
                                navController.navigate("add_title")
                            }
                        )
                    }

                    // Pantalla per afegir nova peli
                    composable("add_title") {
                        AddTitleScreen(
                            viewModel = netflixViewModel,
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}