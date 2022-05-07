package io.github.muqhc.kotching

import kotlin.reflect.KClass
import kotlin.reflect.cast
import kotlin.reflect.full.*
import kotlin.reflect.jvm.jvmErasure

class PatternMatchingContext<T>(private val input: T) {
    internal var isEnd = false
    internal lateinit var result: Any

    fun <L : Function<Any>> case(activePattern: ActivePattern<T,L>, running: L) {
        if (isEnd) return
        val inputKClass = activePattern::matching.valueParameters[0].type.jvmErasure
        try {
            result = activePattern.matching(
                input, running
            )
            isEnd = true
        } catch (e: ClassCastException) { }
    }

    operator fun <L : Function<Any>> ActivePattern<T,L>.invoke(running: L) {
        case(this,running)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> case(running: (T) -> Any) {
        if (isEnd) return
        (input as? T)?.let {
            result = running(it)
            isEnd = true
        }
    }

    fun else_(running: (T) -> Any) {
        if (isEnd) return
        result = running(input)
        isEnd = true
    }
}