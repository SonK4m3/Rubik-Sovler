package com.example.rubiksovler.data.model

import com.example.rubiksovler.data.RubikFace
import com.example.rubiksovler.data.RubikFaceColor
import com.example.rubiksovler.data.RubikMove

class Cube(private val level: Int) {

    companion object {
        // front, back, left, right, top, bottom
        val orderColorDefault = listOf(
            RubikFaceColor.BLUE,
            RubikFaceColor.GREEN,
            RubikFaceColor.ORANGE,
            RubikFaceColor.RED,
            RubikFaceColor.YELLOW,
            RubikFaceColor.WHITE
        )

        /*
        0 1 2
        3 4 5
        6 7 8
        */
        enum class Piece(id: Int) {
            CENTER(4),
            LEFT_EDGE(3),
            RIGHT_EDGE(5),
            UP_EDGE(1),
            DOWN_EDGE(7),
            LEFT_UP_CORNER(0),
            LEFT_DOWN_CORNER(6),
            RIGHT_UP_CORNER(2),
            RIGHT_DOWN_CORNER(8)
        }

        enum class Edge(val pos1: Int,val pos2: Int,val face1: RubikFace,val face2: RubikFace) {
            UF(7, 1, RubikFace.UP, RubikFace.FRONT),
            UR(5, 1, RubikFace.UP, RubikFace.RIGHT),
            UB(1, 1, RubikFace.UP, RubikFace.BACK),
            UL(3, 7, RubikFace.UP, RubikFace.LEFT),
            DF(1, 7, RubikFace.DOWN, RubikFace.FRONT),
            DR(5, 7, RubikFace.DOWN, RubikFace.RIGHT),
            DB(7, 7, RubikFace.DOWN, RubikFace.BACK),
            DL(3, 7, RubikFace.DOWN, RubikFace.LEFT),
            FR(5, 3, RubikFace.FRONT, RubikFace.RIGHT),
            BR(3, 5, RubikFace.BACK, RubikFace.RIGHT),
            BL(5, 3, RubikFace.BACK, RubikFace.LEFT),
            FL(3, 5, RubikFace.FRONT, RubikFace.LEFT)
        }

        enum class Corner(pos1: Int, pos2: Int, pos3: Int) {
            URF(8, 0, 2),
            UBR(2, 0, 2),
            ULB(0, 0, 2),
            UFL(6, 0, 2),
            DFR(2, 8, 6),
            DRB(8, 8, 6),
            DBL(6, 8, 6),
            DLF(0, 8, 6)
        }

        val ePos = listOf<Edge>(
            Edge.UF,
            Edge.UR,
            Edge.UB,
            Edge.UL,
            Edge.DF,
            Edge.DR,
            Edge.DB,
            Edge.DL,
            Edge.FR,
            Edge.BR,
            Edge.BL,
            Edge.FL
        )
    }

    /*
            |0|1|2|
            |3|4|5|
            |6|7|8|
    |0|1|2| |0|1|2| |0|1|2| |0|1|2|
    |3|4|5| |3|4|5| |3|4|5| |3|4|5|
    |6|7|8| |6|7|8| |6|7|8| |6|7|8|
            |0|1|2|
            |3|4|5|
            |6|7|8|
     */
    private var F: ArrayList<RubikFaceColor>? = null
    private var B: ArrayList<RubikFaceColor>? = null
    private var L: ArrayList<RubikFaceColor>? = null
    private var R: ArrayList<RubikFaceColor>? = null
    private var U: ArrayList<RubikFaceColor>? = null
    private var D: ArrayList<RubikFaceColor>? = null

    init {
        F = ArrayList()
        B = ArrayList()
        L = ArrayList()
        R = ArrayList()
        U = ArrayList()
        D = ArrayList()

        for (i in 1..(level * level)) {
            F!!.add(orderColorDefault[0])
            B!!.add(orderColorDefault[1])
            L!!.add(orderColorDefault[2])
            R!!.add(orderColorDefault[3])
            U!!.add(orderColorDefault[4])
            D!!.add(orderColorDefault[5])
        }
    }

    fun getF(): ArrayList<RubikFaceColor>? = F
    fun getB(): ArrayList<RubikFaceColor>? = B
    fun getL(): ArrayList<RubikFaceColor>? = L
    fun getR(): ArrayList<RubikFaceColor>? = R
    fun getU(): ArrayList<RubikFaceColor>? = U
    fun getD(): ArrayList<RubikFaceColor>? = D

    fun getLevel(): Int = level

    private fun rotateMatrix(arr: ArrayList<RubikFaceColor>, amount: Boolean = false) {
        val tempMatrix = arr.clone() as ArrayList<RubikFaceColor>

        if (!amount) {
            for (i in 0 until level) {
                for (j in 0 until level) {
                    val newIndex = i * level + j
                    val rotatedIndex = (level - j - 1) * level + i
                    arr[newIndex] = tempMatrix[rotatedIndex]
                }
            }
        } else {
            for (i in 0 until level) {
                for (j in 0 until level) {
                    val newIndex = i * level + j
                    val rotatedIndex = j * level + (level - i - 1)
                    arr[newIndex] = tempMatrix[rotatedIndex]
                }
            }
        }
    }



    private fun swapArrayList(arr1: ArrayList<RubikFaceColor>, arr2: ArrayList<RubikFaceColor>) {
        val tmp = ArrayList(arr1)
        arr1.clear()
        arr1.addAll(arr2)
        arr2.clear()
        arr2.addAll(tmp)
    }

    private fun rotateZ(amount: Boolean = false) {
        if (!amount) {
            rotateMatrix(F!!)
            rotateMatrix(B!!, true)
            rotateMatrix(U!!)
            rotateMatrix(D!!)
            swapArrayList(U!!, R!!)
            swapArrayList(U!!, D!!)
            swapArrayList(U!!, L!!)
        } else {
            rotateMatrix(F!!, true)
            rotateMatrix(B!!)
            rotateMatrix(U!!, true)
            rotateMatrix(D!!, true)
            swapArrayList(U!!, L!!)
            swapArrayList(U!!, D!!)
            swapArrayList(U!!, R!!)
        }
    }

    private fun rotateX(amount: Boolean = false) {
        if (!amount) {
            rotateMatrix(R!!)
            rotateMatrix(L!!, true)
            rotateMatrix(B!!)
            rotateMatrix(B!!)
            rotateMatrix(U!!)
            rotateMatrix(U!!)
            swapArrayList(U!!, B!!)
            swapArrayList(U!!, D!!)
            swapArrayList(U!!, F!!)
        } else {
            rotateMatrix(R!!, true)
            rotateMatrix(L!!)
            rotateMatrix(B!!)
            rotateMatrix(B!!)
            rotateMatrix(D!!)
            rotateMatrix(D!!)
            swapArrayList(U!!, F!!)
            swapArrayList(U!!, D!!)
            swapArrayList(U!!, B!!)
        }
    }

    private fun rotateY(amount: Boolean = false) {
        if (!amount) {
            rotateMatrix(U!!)
            rotateMatrix(D!!, true)
            swapArrayList(F!!, L!!)
            swapArrayList(F!!, B!!)
            swapArrayList(F!!, R!!)
        } else {
            rotateMatrix(U!!, true)
            rotateMatrix(D!!)
            swapArrayList(F!!, R!!)
            swapArrayList(F!!, B!!)
            swapArrayList(F!!, L!!)
        }
    }

    private fun swapColumn(
        arr1: ArrayList<RubikFaceColor>,
        arr2: ArrayList<RubikFaceColor>,
        pos1: Int,
        pos2: Int,
        amount: Boolean = false
    ) {
        if (pos1 < 0 || pos1 >= level || pos2 < 0 || pos2 >= level)
            return
        for (i in 0 until level) {
            if (!amount) {
                val tmp = arr1[pos1 + level * i]
                arr1[pos1 + level * i] = arr2[pos2 + level * i]
                arr2[pos2 + level * i] = tmp
            } else {
                val tmp = arr1[pos1 + level * i]
                arr1[pos1 + level * i] = arr2[pos2 + level * (level - 1 - i)]
                arr2[pos2 + level * (level - 1 - i)] = tmp
            }
        }
    }

    private fun swapRow(
        arr1: ArrayList<RubikFaceColor>,
        arr2: ArrayList<RubikFaceColor>,
        pos1: Int,
        pos2: Int,
        amount: Boolean = false
    ) {
        if (pos1 < 0 || pos1 >= level || pos2 < 0 || pos2 >= level)
            return
        for (i in 0 until level) {
            if (!amount) {
                val tmp = arr1[pos1 * level + i]
                arr1[pos1 * level + i] = arr2[pos2 * level + i]
                arr2[pos2 * level + i] = tmp
            } else {
                val tmp = arr1[pos1 * level + i]
                arr1[pos1 * level + i] = arr2[pos2 * level + level - 1 - i]
                arr2[pos2 * level + level - 1 - i] = tmp
            }
        }
    }

    private fun swapRowCol(
        arr_row: ArrayList<RubikFaceColor>,
        arr_col: ArrayList<RubikFaceColor>,
        pos_row: Int,
        pos_col: Int,
        amount: Boolean = false
    ) {
        if (pos_row >= level || pos_row < 0 || pos_col >= level || pos_col < 0)
            return
        for (i in 0 until level) {
            if (!amount) {
                val tmp = arr_row[pos_row * level + i]
                arr_row[pos_row * level + i] = arr_col[pos_col + i * level]
                arr_col[pos_col + i * level] = tmp
            } else {
                val tmp = arr_row[pos_row * level + i]
                arr_row[pos_row * level + i] = arr_col[pos_col + (level - 1 - i) * level]
                arr_col[pos_col + (level - 1 - i) * level] = tmp
            }
        }
    }

    private fun moveLayerF(counter: Boolean = false) {
        if (!counter) {
            // Up -> Right -> Down -> Left -> Up
            rotateMatrix(F!!)
            swapRowCol(U!!, R!!, 2, 0)
            swapRow(U!!, D!!, 2, 0, true)
            swapRowCol(U!!, L!!, 2, 2, true)

        } else {
            // Up -> Left -> Down -> Right -> Up
            rotateMatrix(F!!, true)
            swapRowCol(U!!, L!!, 2, 2, true)
            swapRow(U!!, D!!, 2, 0, true)
            swapRowCol(U!!, R!!, 2, 0)
        }
    }

    private fun moveLayerB(counter: Boolean = false) {
        if (!counter) {
            // Up -> Left -> Down -> Right -> Up
            rotateMatrix(B!!)
            swapRowCol(U!!, L!!, 0, 0, true)
            swapRow(U!!, D!!, 0, 2, true)
            swapRowCol(U!!, R!!, 0, 2)
        } else {
            // Up -> Right -> Down -> Left -> Up
            rotateMatrix(B!!, true)
            swapRowCol(U!!, R!!, 0, 2)
            swapRow(U!!, D!!, 0, 2, true)
            swapRowCol(U!!, L!!, 0, 2, true)
        }
    }

    private fun moveLayerL(counter: Boolean = false) {
        if (!counter) {
            // Up -> Face -> Down -> Back -> Up
            rotateMatrix(L!!)
            swapColumn(U!!, F!!, 0, 0)
            swapColumn(U!!, D!!, 0, 0)
            swapColumn(U!!, B!!, 0, 2, true)

        } else {
            // Up -> Back -> Down -> Face -> Up
            rotateMatrix(L!!, true)
            swapColumn(U!!, B!!, 0, 2, true)
            swapColumn(U!!, D!!, 0, 0)
            swapColumn(U!!, F!!, 0, 0)
        }
    }

    private fun moveLayerR(counter: Boolean = false) {
        if (!counter) {
            // Up -> Back -> Down -> Face -> Up
            rotateMatrix(R!!)
            swapColumn(U!!, B!!, 2, 0, true)
            swapColumn(U!!, D!!, 2, 2)
            swapColumn(U!!, F!!, 2, 2)
        } else {
            // Up -> Face -> Down -> Back -> Up
            rotateMatrix(R!!, true)
            swapColumn(U!!, F!!, 2, 2)
            swapColumn(U!!, D!!, 2, 2)
            swapColumn(U!!, B!!, 2, 0, true)
        }
    }

    private fun moveLayerU(counter: Boolean = false) {
        if (!counter) {
            // Face -> Left -> Back -> Right -> Face
            rotateMatrix(U!!)
            swapRow(F!!, L!!, 0, 0)
            swapRow(F!!, B!!, 0, 0)
            swapRow(F!!, R!!, 0, 0)
        } else {
            // Face -> Right -> Back -> Left -> Face
            rotateMatrix(U!!, true)
            swapRow(F!!, R!!, 0, 0)
            swapRow(F!!, B!!, 0, 0)
            swapRow(F!!, L!!, 0, 0)
        }
    }

    private fun moveLayerD(counter: Boolean = false) {
        if (!counter) {
            // Face -> Right -> Back -> Left -> Face
            rotateMatrix(D!!)
            swapRow(F!!, R!!, 2, 2)
            swapRow(F!!, B!!, 2, 2)
            swapRow(F!!, L!!, 2, 2)
        } else {
            // Face -> Left -> Back -> Right -> Face
            rotateMatrix(D!!, true)
            swapRow(F!!, L!!, 2, 2)
            swapRow(F!!, B!!, 2, 2)
            swapRow(F!!, R!!, 2, 2)
        }
    }

    fun moveLayer(move: RubikMove) {
        when (move) {
            RubikMove.F -> moveLayerF()
            RubikMove.F_ -> moveLayerF(true)
            RubikMove.B -> moveLayerB()
            RubikMove.B_ -> moveLayerB(true)
            RubikMove.L -> moveLayerL()
            RubikMove.L_ -> moveLayerL(true)
            RubikMove.R -> moveLayerR()
            RubikMove.R_ -> moveLayerR(true)
            RubikMove.U -> moveLayerU()
            RubikMove.U_ -> moveLayerU(true)
            RubikMove.D -> moveLayerD()
            RubikMove.D_ -> moveLayerD(true)
            RubikMove.X -> rotateX()
            RubikMove.X_ -> rotateX(true)
            RubikMove.Y -> rotateY()
            RubikMove.Y_ -> rotateY(true)
            RubikMove.Z -> rotateZ()
            RubikMove.Z_ -> rotateZ(true)
            else -> return
        }
    }

    fun moveLayers(moves: List<RubikMove>) {
        for(move in moves) {
            this.moveLayer(move)
        }
    }

    fun changeColor(position: Int, color: RubikFaceColor) {
        if (position < 0 || position > level * level - 1)
            return
        F?.set(position, color)
    }

    fun getFace(face: RubikFace): ArrayList<RubikFaceColor>? {
        return when (face) {
            RubikFace.FRONT -> F
            RubikFace.BACK -> B
            RubikFace.LEFT -> L
            RubikFace.RIGHT -> R
            RubikFace.UP -> U
            RubikFace.DOWN -> D
        }
    }

    fun getFaceColor(face: RubikFace): RubikFaceColor? {
        return when (face) {
            RubikFace.FRONT -> F?.get(4)
            RubikFace.BACK -> B?.get(4)
            RubikFace.LEFT -> L?.get(4)
            RubikFace.RIGHT -> R?.get(4)
            RubikFace.UP -> U?.get(4)
            RubikFace.DOWN -> D?.get(4)
        }
    }

    fun getEdgeColor(edge: Edge): ArrayList<RubikFaceColor> {
        val colors = ArrayList<RubikFaceColor>()
        colors.add(getFace(edge.face1)!![edge.pos1])
        colors.add(getFace(edge.face2)!![edge.pos2])
        return colors
    }

    fun copy(): Cube {
        val copiedCube = Cube(level)

        copiedCube.F = ArrayList(F)
        copiedCube.B = ArrayList(B)
        copiedCube.L = ArrayList(L)
        copiedCube.R = ArrayList(R)
        copiedCube.U = ArrayList(U)
        copiedCube.D = ArrayList(D)

        return copiedCube
    }
}