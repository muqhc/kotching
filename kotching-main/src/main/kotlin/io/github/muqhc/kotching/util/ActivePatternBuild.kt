package io.github.muqhc.kotching.util

import io.github.muqhc.kotching.ActivePattern
import io.github.muqhc.kotching.ActivePatternBuildContext
import io.github.muqhc.kotching.ActivePatternPredicateBuildContext

fun <T,L:Function<Any>> ActivePattern(init: ActivePatternBuildContext<T, L>.() -> Any) =
    object : ActivePattern<T, L> {
        val block = init
        override fun matching(input: T, lambda: L): Any =
            ActivePatternBuildContext(input, lambda).run(block)
    }

fun <T,L:Function<Any>> ActivePattern<T,L>.require(predicate: ActivePatternPredicateBuildContext<T>.() -> Boolean): ActivePattern<T,L> =
    object : ActivePattern<T,L> by this {
        override fun checkRequire(input: @UnsafeVariance T): Boolean = super.checkRequire(input) && ActivePatternPredicateBuildContext(input).run(predicate)
    }

operator fun <T,L:Function<Any>> ActivePattern<T,L>.invoke(predicate: ActivePatternPredicateBuildContext<T>.() -> Boolean): ActivePattern<T,L> =
    require(predicate)