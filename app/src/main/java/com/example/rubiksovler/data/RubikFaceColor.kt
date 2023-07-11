package com.example.rubiksovler.data

import android.graphics.Color

enum class RubikFaceColor(val rgb: Int) {
    Unspecified(-1),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    RED(Color.RED),
    ORANGE(0xFFFFA500.toInt()),
    YELLOW(Color.YELLOW),
    WHITE(Color.WHITE)
}