package io.github.muqhc.kotching

import io.github.muqhc.kotching.exception.NoMatchException
import kotlin.reflect.full.cast
import kotlin.reflect.full.valueParameters
import kotlin.reflect.jvm.jvmErasure

@KotchingDsl
fun <T> match(input: T, block: PatternMatchingContext<T>.() -> Unit) =
    PatternMatchingContext(input).apply(block).run { if (isEnd) result else throw NoMatchException("Couldn't match any patterns or types.") }

@KotchingDsl
@JvmName("match1")
fun <T> T.match(block: PatternMatchingContext<T>.() -> Unit) =
    match(this,block)

@KotchingDsl
fun <T> matching(block: PatternMatchingContext<T>.() -> Unit) =
    { input: T ->
        PatternMatchingContext(input).apply(block)
            .run { if (isEnd) result else throw NoMatchException("Couldn't match any patterns or types.") }
    }

@KotchingDsl
@Suppress("UNCHECKED_CAST")
fun <T,L : Function<R>,R:Any> T.case(activePattern: ActivePattern<T,L>, running: L): R? {
    val inputKClass = activePattern::matching.valueParameters[0].type.jvmErasure
    if (runCatching { inputKClass.cast(this) }.isFailure) return null
    return activePattern.matching(
        this, running
    ) as R
}
