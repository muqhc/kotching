package io.github.muqhc.kotching.test

import io.github.muqhc.kotching.*
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


    val textList = listOf(
        "123","12.32","abc","100","1OO"
    ).map { it.toIntOrString() }

    textList.map(matching {
        case(Either.Left()) { println("$it is Integer!") }
        case(Either.Right()) { println("$it is Not Integer!") }
    })
}