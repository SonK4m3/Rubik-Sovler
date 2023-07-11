package com.example.rubiksovler.data

import com.example.rubiksovler.data.model.Cube

interface RubikSolver {
    fun solve(cube: Cube): List<RubikMove>
}