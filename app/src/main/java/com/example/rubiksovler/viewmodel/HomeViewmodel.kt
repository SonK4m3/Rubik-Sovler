package com.example.rubiksovler.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.rubiksovler.data.model.Cube

class HomeViewmodel: ViewModel() {

    val cube: MutableState<Cube> = mutableStateOf(createCube())

    private fun createCube(): Cube {
        return Cube(3)
    }

    fun updateCube(newCube: Cube) {
        cube.value = newCube
    }
}