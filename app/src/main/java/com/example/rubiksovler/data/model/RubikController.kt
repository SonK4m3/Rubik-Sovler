package com.example.rubiksovler.data.model

import android.util.Log
import com.example.rubiksovler.data.*

class RubikController(private val cube: Cube) {
    private val history: Stack<RubikMove> = Stack()
    private val historyTemp: Stack<RubikMove> = Stack()
    private lateinit var rubikSolver: RubikSolver

    init {
        when (cube.getLevel()) {
            3 -> rubikSolver = RubikSolverFactory.createSolver(RubikSolverFactory.CubeSize.SIZE_3x3)
            4 -> rubikSolver = RubikSolverFactory.createSolver(RubikSolverFactory.CubeSize.SIZE_4x4)
        }
    }

    private fun getCounterMove(move: RubikMove): RubikMove = (
            when (move) {
                RubikMove.F -> RubikMove.F_
                RubikMove.F_ -> RubikMove.F
                RubikMove.B -> RubikMove.B_
                RubikMove.B_ -> RubikMove.B
                RubikMove.L -> RubikMove.L_
                RubikMove.L_ -> RubikMove.L
                RubikMove.R -> RubikMove.R_
                RubikMove.R_ -> RubikMove.R
                RubikMove.U -> RubikMove.U_
                RubikMove.U_ -> RubikMove.U
                RubikMove.D -> RubikMove.D_
                RubikMove.D_ -> RubikMove.D
                RubikMove.X -> RubikMove.X_
                RubikMove.X_ -> RubikMove.X
                RubikMove.Y -> RubikMove.Y_
                RubikMove.Y_ -> RubikMove.Y
                RubikMove.Z -> RubikMove.Z_
                else -> RubikMove.Z
            }
            )

    fun move(rubikMove: RubikMove) {
        history.push(rubikMove)
        historyTemp.popAll()
        cube.moveLayer(rubikMove)
    }

    fun undo() {
        if (history.isEmpty())
            return
        val lastMove = history.pop()
        if (lastMove != null) {
            historyTemp.push(lastMove)
            cube.moveLayer(getCounterMove(lastMove))
        }
    }

    fun redo() {
        if (historyTemp.isEmpty())
            return
        val preMove = historyTemp.pop()
        if (preMove != null) {
            history.push(preMove)
            cube.moveLayer(preMove)
        }
    }

    fun getCurrentMove(): RubikMove? = history.peek()

    fun getHistoryLength(): Int = history.size()

    fun getHistory(): Stack<RubikMove> = history

    fun randomMove() {
        cube.moveLayer(RubikMove.F_)
        cube.moveLayer(RubikMove.R)
        cube.moveLayer(RubikMove.U)
        cube.moveLayer(RubikMove.R_)
        cube.moveLayer(RubikMove.L)
        cube.moveLayer(RubikMove.B)
    }

    fun changeColor(pos: Int, color: RubikFaceColor) {
        cube.changeColor(pos, color)
    }

    private fun countingColor(face: List<RubikFaceColor>, color: RubikFaceColor): Int {
        var res = 0
        for (cell in face) {
            if (cell == color) res++
        }
        return res
    }

    fun checkTotalColors(): Boolean {
        val level = cube.getLevel()
        val orderColor = Cube.orderColorDefault
        for (color in orderColor) {
            val cnt = countingColor(cube.getF()!!, color) +
                    countingColor(cube.getB()!!, color) +
                    countingColor(cube.getL()!!, color) +
                    countingColor(cube.getR()!!, color) +
                    countingColor(cube.getU()!!, color) +
                    countingColor(cube.getD()!!, color)
            if (cnt != level * level) return false
        }
        return true
    }

    fun solve(): List<RubikMove> {
        val step = rubikSolver.solve(cube)
        Log.d("AAA", "${step.toString()}")
        return step
    }
}