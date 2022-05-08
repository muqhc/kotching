package io.github.muqhc.kotching.util

import io.github.muqhc.kotching.exception.NoMatchException
import kotlin.reflect.KProperty

class Exported(internal val valueLazy: (() -> Any)?) {
    fun get() = valueLazy!!.invoke()

    @Suppress("UNCHECKED_CAST")
    operator fun <T> getValue(thisRef: Any?, prop: KProperty<*>) =
        get() as T
}
