package io.github.muqhc.kotching

import kotlin.reflect.full.cast
import kotlin.reflect.full.valueParameters
import kotlin.reflect.jvm.jvmErasure

fun <T> match(input: T, block: PatternMatchingContext<T>.() -> Unit) =
    PatternMatchingContext(input).apply(block).result

@JvmName("match1")
fun <T> T.match(block: PatternMatchingContext<T>.() -> Unit) =
    PatternMatchingContext(this).apply(block).result

@Suppress("UNCHECKED_CAST")
fun <T,L : Function<R>,R:Any> T.case(activePattern: ActivePattern<T,L>, running: L): R? {
    val inputKClass = activePattern::matching.valueParameters[0].type.jvmErasure
    if (runCatching { inputKClass.cast(this) }.isFailure) return null
    return activePattern.matching(
        this, running
    ) as R
}
