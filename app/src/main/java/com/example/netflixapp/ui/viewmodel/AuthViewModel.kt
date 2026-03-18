package com.example.netflixapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.netflixapp.utils.SharedPrefsManager

// ViewModel encarregat de gestionar la lògica d'autenticació (login i registre)
class AuthViewModel(application: Application) : AndroidViewModel(application) {

    // Instància del gestor de SharedPreferences
    private val prefsManager = SharedPrefsManager(application)

    // Missatges que es mostraran a la UI (errors, confirmacions...)
    private val _authMessage = MutableLiveData<String>("")
    val authMessage: LiveData<String> = _authMessage

    // Estat de login (true si l'usuari ha iniciat sessió correctament)
    private val _isLoggedIn = MutableLiveData(false)
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    // Funció per registrar un usuari
    fun register(username: String, password: String) {

        // Validem que els camps no estiguin buits
        if (username.isBlank() || password.isBlank()) {
            _authMessage.value = "Omple tots els camps"
            return
        }

        // Intentem registrar l'usuari
        val success = prefsManager.registerUser(username, password)

        // Actualitzem el missatge segons el resultat
        _authMessage.value = if (success) {
            "Usuari registrat correctament"
        } else {
            "Aquest usuari ja existeix"
        }
    }

    // Funció per fer login
    fun login(username: String, password: String) {

        // Validem camps buits
        if (username.isBlank() || password.isBlank()) {
            _authMessage.value = "Omple tots els camps"
            return
        }

        // Comprovem si l'usuari i contrasenya són correctes
        val success = prefsManager.loginUser(username, password)

        if (success) {
            // Si el login és correcte, canviem l'estat a true
            _isLoggedIn.value = true
            _authMessage.value = "Login correcte"
        } else {
            // Si no, mostrem error
            _authMessage.value = "Usuari o contrasenya incorrectes"
        }
    }

    // Funció per reiniciar l'estat de login (per exemple, logout)
    fun resetLoginState() {
        _isLoggedIn.value = false
    }
}