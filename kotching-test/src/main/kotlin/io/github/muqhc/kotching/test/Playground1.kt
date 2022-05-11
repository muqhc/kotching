package io.github.muqhc.kotching.test

import io.github.muqhc.kotching.dsl.*
import io.github.muqhc.kotching.util.monad.Either

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

fun String.toIntOrString(): Either<Int,String> =
    toIntOrNull()?.let { Either(it, w) } ?: Either(w,this)

fun main() {
    val magenta = Color(0xFF00FF)

    printColor(magenta)
    printColor(strangeColor(magenta) as Color)


    match("1OO".toIntOrString()) {
        case(Either.Left()) { println("$it is Int") }
        case(Either.Right()) { println("$it is String") }
    }
}