package io.github.muqhc.kotching.test

import io.github.muqhc.kotching.ActivePattern
import io.github.muqhc.kotching.match

class Color(val hex: Int)

val RGB =
    ActivePattern<Color, (red:Int,green:Int,blue:Int) -> Any> { input, lambda ->
        val blue = input.hex % 256
        val green = (input.hex shr 8) % 256
        val red = (input.hex shr 16) % 256
        lambda(red,green,blue)
    }

class Car(val speed: Double, val year: Int) {
    companion object: ActivePattern<Car, (speed:Double,year:Int) -> Any> by ActivePattern({ input, lambda ->
        lambda(input.speed,input.year)
    })
}


fun main() {
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
        match(obj) {
            case(RGB) { red, green, bluee -> println("Color(red=$red,green=$green,blue=$bluee)") }
            case(Car) { speed, year -> println("Car(speed=$speed,year=$year)") }
            else_ { throw Exception("Not a Color") }
        }
    }
}