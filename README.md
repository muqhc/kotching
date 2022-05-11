# kotching
pattern matching in kotlin

## Example Usage

### #1

```kotlin
// import
package io.github.muqhc.kotching.test

import io.github.muqhc.kotching.*
import io.github.muqhc.kotching.dsl.*

// Just a 'Color' class
class Color(val hex: Int)

val RGB = // create Active Pattern for 'Color'
    ActivePattern<Color, (red:Int,green:Int,blue:Int) -> Any> {
        val blue = input.hex % 256
        val green = (input.hex shr 8) % 256
        val red = (input.hex shr 16) % 256
        export(red,green,blue)
    }

// 'Car' the class contains Active Pattern ( with Companion )
class Car(val speed: Double, val year: Int) {
    // create Active Pattern with Companion Object
    companion object: ActivePattern<Car, (speed:Double,year:Int) -> Any> by ActivePattern({
        export(input.speed, input.year)
    })
}


fun main() {
    // Just an object list for test
    val objList = listOf(
        Car(101.5,1997),
        Color(0xABCDEF),
        Color(0xAAFFAA),
        Color(0x990000),
        Car(10105.2,4096),
        Car(12.0,182),
        Color(0xFEDCBA),
        Car(102331.1,12394),
    )

    objList.forEach { obj ->
        // do pattern match
        match(obj) {
            // use Active Pattern
            case(RGB) { red, green, blue ->
                println("Color(red=$red,green=$green,blue=$blue)")
            }
            // use Active Pattern with Companion
            case(Car) { speed, year ->
                println("Car(speed=$speed,year=$year)")
            }
            // when match nothing
            else_ { throw Exception("Not a Color or Car") }
        }
    }

    // You can do pattern match like this
    objList.map(matching {
        case(RGB) { red, green, blue ->
            println("Color(red=$red,green=$green,blue=$blue)")
        }
        case(Car) { speed, year ->
            println("Car(speed=$speed,year=$year)")
        }
        else_ { throw Exception("Not a Color or Car") }
    })

    // Do Single Pattern match
    Color(0xABCDEF).case(RGB) { _,_,_ -> println("hello") }

    // get value from pattern matching
    val v1 = match(Color(0x123456)) {
        case(RGB) { red, green, blue ->
            "Color(red=$red,green=$green,blue=$blue)"
        }
    } as String

    println(v1)
}
```
[io.github.muqhc.kotching.test.Playground0Kt](kotching-test/src/main/kotlin/io/github/muqhc/kotching/test/Playground0.kt)

### #2

```kotlin
// import
import io.github.muqhc.kotching.dsl.*
import io.github.muqhc.kotching.util.monad.Either
import io.github.muqhc.kotching.util.monad.Either.Companion.Left
import io.github.muqhc.kotching.util.monad.Either.Companion.Right

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

    // Either Monad pattern matching
    match(intOrString) {
        case(Left()) { println("$it is Int") }
        case(Right()) { println("$it is String") }
    }
}
```
[io.github.muqhc.kotching.test.Playground1Kt](kotching-test/src/main/kotlin/io/github/muqhc/kotching/test/Playground1.kt)

## Note
+ Inspired by Active Pattern in F#(programming language).