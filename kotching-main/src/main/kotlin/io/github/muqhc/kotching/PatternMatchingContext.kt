package io.github.muqhc.kotching

import io.github.muqhc.kotching.util.Exported
import kotlin.reflect.full.*
import kotlin.reflect.jvm.jvmErasure

@KotchingDsl
class PatternMatchingContext<T>(val input: T) {
    internal var isEnd = false
    internal var result = Exported(null)

    @KotchingDsl
    fun <L : Function<Any>> case(activePattern: ActivePattern<T,L>, running: L) {
        if (isEnd) return
        try {
            if (activePattern.checkRequire(input)) {
                result = activePattern.matching(
                    input, running
                ).let { Exported { it } }
                isEnd = true
            }
        } catch (e: ClassCastException) {  }
    }

    @Suppress("UNCHECKED_CAST")
    @KotchingDsl
    fun <T> case(running: (T) -> Any) {
        if (isEnd) return
        (input as? T)?.let {
            result = Exported { running(it) }
            isEnd = true
        }
    }

    @KotchingDsl
    fun else_(running: (T) -> Any) {
        if (isEnd) return
        result = Exported { running(input) }
        isEnd = true
    }
}