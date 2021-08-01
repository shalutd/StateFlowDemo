package com.shalu.stateflowdemo

import kotlinx.coroutines.flow.flow

class MainRepository {
    fun getPlaces() = flow {
        emit(listOf("Canada, US, India").joinToString())
    }
}