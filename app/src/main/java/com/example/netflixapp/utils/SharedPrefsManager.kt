package com.example.netflixapp.utils

import android.content.Context

// Classe encarregada de gestionar l'emmagatzematge local d'usuaris, utilitzant SharedPreferences (guardat en el dispositiu)
class SharedPrefsManager(context: Context) {

    // Creem o accedim a un fitxer de preferències anomenat "users_prefs"
    private val prefs = context.getSharedPreferences("users_prefs", Context.MODE_PRIVATE)

    // Funció per registrar un usuari nou
    fun registerUser(username: String, password: String): Boolean {

        // Si ja existeix un usuari amb aquest nom, no deixem registrar-lo
        if (prefs.contains(username)) {
            return false
        }

        // Guardem l'usuari i la seva contrasenya: Clau = username, Valor = password
        prefs.edit().putString(username, password).apply()

        // Retornem true perquè s'ha registrat correctament
        return true
    }

    // Funció per fer login
    fun loginUser(username: String, password: String): Boolean {

        // Recuperem la contrasenya guardada per aquest usuari
        val savedPassword = prefs.getString(username, null)

        // Comparem la contrasenya guardada amb la introduïda
        return savedPassword == password
    }
}