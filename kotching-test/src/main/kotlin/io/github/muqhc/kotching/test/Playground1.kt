package io.github.muqhc.kotching.test

import io.github.muqhc.kotching.*
import io.github.muqhc.kotching.dsl.*

val strangeColor = function(RGB) { r,g,b ->
    Color(
        (255-r)*256*256+
                (255-g)*256+
                (255-b)
    )
}

val printColor = function(RGB) { r,g,b ->
    println("Color(red=$r,green=$g,blue=$b)")
}

fun main() {
    val magenta = Color(0xFF00FF)
    printColor(magenta)
    printColor(strangeColor(magenta) as Color)
}