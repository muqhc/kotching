package io.github.muqhc.kotching.test

import io.github.muqhc.kotching.ActivePattern
import io.github.muqhc.kotching.match

// Just a 'Color' class
class Color(val hex: Int)

val RGB = // create Active Pattern for 'Color'
    ActivePattern<Color, (red:Int,green:Int,blue:Int) -> Any> { input, lambda ->
        val blue = input.hex % 256
        val green = (input.hex shr 8) % 256
        val red = (input.hex shr 16) % 256
        lambda(red,green,blue)
    }

// 'Car' the class contains Active Pattern ( with Companion )
class Car(val speed: Double, val year: Int) {
    // create Active Pattern with Companion Object
    companion object: ActivePattern<Car, (speed:Double,year:Int) -> Any> by ActivePattern({ input, lambda ->
        lambda(input.speed,input.year)
    })
}


fun main() {
    // Just an object list for test
    val objList = listOf(
        Color(0xABCDEF),
        Car(101.5,1997),
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
            else_ { throw Exception("Not a Color") }
        }
    }
}