package com.example.netflixapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netflixapp.data.model.NetflixTitle
import com.example.netflixapp.data.repository.NetflixRepository
import kotlinx.coroutines.launch

class NetflixViewModel : ViewModel() {

    private val repository = NetflixRepository()

    private val _titles = MutableLiveData<List<NetflixTitle>>(emptyList())
    val titles: LiveData<List<NetflixTitle>> = _titles

    private val _selectedTitle = MutableLiveData<NetflixTitle?>(null)
    val selectedTitle: LiveData<NetflixTitle?> = _selectedTitle

    private val _message = MutableLiveData<String>("")
    val message: LiveData<String> = _message

    fun loadAllTitles() {
        viewModelScope.launch {
            try {
                _titles.value = repository.getAllTitles()
            } catch (e: Exception) {
                _message.value = "Error carregant dades: ${e.message}"
            }
        }
    }

    fun loadTitlesByType(type: String) {
        viewModelScope.launch {
            try {
                _titles.value = repository.getTitlesByType(type)
            } catch (e: Exception) {
                _message.value = "Error filtrant dades: ${e.message}"
            }
        }
    }

    fun loadTitleById(id: String) {
        viewModelScope.launch {
            try {
                _selectedTitle.value = repository.getTitleById(id)
            } catch (e: Exception) {
                _message.value = "Error carregant detall: ${e.message}"
            }
        }
    }

    fun insertTitle(title: NetflixTitle) {
        viewModelScope.launch {
            try {
                val response = repository.insertTitle(title)

                if (response.isSuccessful) {
                    _message.value = response.body()?.message ?: "Inserit correctament"
                } else {
                    _message.value = "Error en inserir"
                }
            } catch (e: Exception) {
                _message.value = "Error POST: ${e.message}"
            }
        }
    }
}