package io.github.muqhc.kotching.test

import io.github.muqhc.kotching.dsl.*
import io.github.muqhc.kotching.util.monad.Either

// create pattern matching function
val printColor = function(RGB) { r,g,b ->
    println("Color(red=$r,green=$g,blue=$b)")
}

// return Either<Int,String>
fun String.toIntOrString(): Either<Int,String> =
    toIntOrNull()?.let { Either(it, w) } ?: Either(w,this)

fun main() {
    // a Color
    val magenta = Color(0xFF00FF)

    // print rgb of color
    printColor(magenta) // Color(red=255,green=0,blue=255)


    // either int or string ( Either<Int,String> )
    val intOrString = "3OO".toIntOrString()

    // 'Either' monad pattern matching
    match(intOrString) {
        case(Either.Left()) { println("$it is Int") }
        case(Either.Right()) { println("$it is String") }
    }
}