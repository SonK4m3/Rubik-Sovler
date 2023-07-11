package com.example.rubiksovler.data

import android.util.Log
import com.example.rubiksovler.data.model.Cube

class RubikSolver3x3 : RubikSolver {

    private val orderColor = Cube.orderColorDefault

    private fun solveWhiteCross(cube: Cube): ArrayList<RubikMove> {
        val tempCube = cube.copy()
        val rubikMove = ArrayList<RubikMove>()

        for(i in 0 until 4) {
            val crossColor = cube.getFaceColor(RubikFace.DOWN)
            val centerColor = cube.getFaceColor(RubikFace.FRONT)
            for (edge: Cube.Companion.Edge in Cube.ePos) {
                val edgeColors = cube.getEdgeColor(edge)
                if ((edgeColors[0] == crossColor && edgeColors[1] == centerColor) || (edgeColors[1] == crossColor && edgeColors[0] == centerColor)) {
                    var tmpMove = ArrayList<RubikMove>()
                    if (edge.face1 == RubikFace.DOWN) {
                        when (edge.face2) {
                            RubikFace.RIGHT -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.R,
                                            RubikMove.F,
                                            RubikMove.R_
                                        )
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.R,
                                            RubikMove.R,
                                            RubikMove.U,
                                            RubikMove.F,
                                            RubikMove.F
                                        )
                                    )
                                }
                            }
                            RubikFace.BACK -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.B,
                                            RubikMove.B,
                                            RubikMove.U,
                                            RubikMove.R_,
                                            RubikMove.F,
                                            RubikMove.R
                                        )
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.B,
                                            RubikMove.B,
                                            RubikMove.U,
                                            RubikMove.U,
                                            RubikMove.F,
                                            RubikMove.F
                                        )
                                    )
                                }
                            }
                            RubikFace.LEFT -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.L,
                                            RubikMove.F_,
                                            RubikMove.L
                                        )
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.L,
                                            RubikMove.L,
                                            RubikMove.U_,
                                            RubikMove.F,
                                            RubikMove.F
                                        )
                                    )
                                }
                            }
                            else -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.F,
                                            RubikMove.F,
                                            RubikMove.U_,
                                            RubikMove.R_,
                                            RubikMove.F,
                                            RubikMove.R
                                        )
                                    )
                                }
                            }
                        }
                    } else if (edge.face1 == RubikFace.FRONT) {
                        when (edge.face2) {
                            RubikFace.RIGHT -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(RubikMove.F)
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.D,
                                            RubikMove.R_,
                                            RubikMove.D_,
                                            RubikMove.R
                                        )
                                    )
                                }
                            }
                            else -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(RubikMove.F_)
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.D_,
                                            RubikMove.L,
                                            RubikMove.D,
                                            RubikMove.L_
                                        )
                                    )
                                }
                            }
                        }
                    } else if (edge.face1 == RubikFace.BACK) {
                        when (edge.face2) {
                            RubikFace.RIGHT -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.D,
                                            RubikMove.D,
                                            RubikMove.B_,
                                            RubikMove.D,
                                            RubikMove.D
                                        )
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.D,
                                            RubikMove.R,
                                            RubikMove.D_,
                                        )
                                    )
                                }
                            }
                            else -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.D,
                                            RubikMove.D,
                                            RubikMove.B,
                                            RubikMove.D,
                                            RubikMove.D
                                        )
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.D_,
                                            RubikMove.L_,
                                            RubikMove.D,
                                        )
                                    )
                                }
                            }
                        }
                    } else if (edge.face1 == RubikFace.UP) {
                        when (edge.face2) {
                            RubikFace.RIGHT -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.R_,
                                            RubikMove.F,
                                            RubikMove.R
                                        )
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.U,
                                            RubikMove.F,
                                            RubikMove.F
                                        )
                                    )
                                }
                            }
                            RubikFace.BACK -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.U,
                                            RubikMove.R_,
                                            RubikMove.F,
                                            RubikMove.R
                                        )
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.U,
                                            RubikMove.U,
                                            RubikMove.F,
                                            RubikMove.F
                                        )
                                    )
                                }
                            }
                            RubikFace.LEFT -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.L,
                                            RubikMove.F_,
                                            RubikMove.L_
                                        )
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.U_,
                                            RubikMove.F,
                                            RubikMove.F
                                        )
                                    )
                                }
                            }
                            else -> {
                                if (edgeColors[1] == crossColor) {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.U_,
                                            RubikMove.R_,
                                            RubikMove.F,
                                            RubikMove.R
                                        )
                                    )
                                } else {
                                    tmpMove.addAll(
                                        listOf(
                                            RubikMove.F,
                                            RubikMove.F
                                        )
                                    )
                                }
                            }
                        }
                    }

                    tempCube.moveLayers(tmpMove)
                    rubikMove.addAll(tmpMove)
                    tempCube.moveLayer(RubikMove.Y)
                    rubikMove.add(RubikMove.Y)
                    break
                }
            }
        }
        return rubikMove
    }

    private fun solveWhiteCorners() {

    }

    private fun solveSecondLayer() {

    }

    private fun solveYellowCross() {

    }

    private fun solveYellowEdges() {

    }

    private fun solvePermuteCorners() {

    }

    override fun solve(cube: Cube): List<RubikMove> {
        var steps = ArrayList<RubikMove>()

        val solvingCross = solveWhiteCross(cube)

        steps.addAll(solvingCross)

        return steps
    }
}