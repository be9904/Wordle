package edu.skku.cs.pa1

class WordleLetter(
    var letter: Char,
    var backgroundColor: Color = Color.GRAY,
    var textColor: Color = Color.WHITE
){
    // constructor
    init{
        println("Color is " + Integer.toHexString(backgroundColor.rgb))
    }

    fun changeLetter(letter: Char)
    {
        this.letter = letter
    }

    fun changeColor(color: Color)
    {
        backgroundColor = color
        textColor = when(color){
            Color.GREEN, Color.YELLOW -> Color.BLACK
            Color.GRAY -> Color.WHITE
            else -> Color.BLACK // default color
        }
    }
}

enum class Color(val rgb: Int) {
    GREEN(0x99F691),
    YELLOW(0xFFE46F),
    GRAY(0x787C7E),
    BLACK(0x000000),
    WHITE(0xFFFFFF)
}
