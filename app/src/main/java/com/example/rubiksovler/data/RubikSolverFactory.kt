package com.example.rubiksovler.data

class RubikSolverFactory {
    enum class CubeSize {
        SIZE_3x3,
        SIZE_4x4
    }

    companion object {
        fun createSolver(cubeSize: CubeSize): RubikSolver {
            return when (cubeSize) {
                CubeSize.SIZE_3x3 -> RubikSolver3x3()
//            CubeSize.SIZE_4x4 -> RubikSolver4x4()
                else -> RubikSolver3x3()
            }
        }
    }


}