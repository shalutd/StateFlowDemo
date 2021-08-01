package com.shalu.stateflowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val mutablePlaces = MutableStateFlow("")
    val places: StateFlow<String> = mutablePlaces
    val inputPlace = MutableStateFlow("")

    fun loadPlaceNames() {
        viewModelScope.launch {
            mainRepository.getPlaces().catch { e ->
                mutablePlaces.value = e.toString()
            }.combine(inputPlace) { existingPlaces, inputPlace ->
                if (inputPlace.isEmpty())
                    existingPlaces
                else "$existingPlaces, $inputPlace"
            }.collect {
                mutablePlaces.value = it
            }
        }
    }
}