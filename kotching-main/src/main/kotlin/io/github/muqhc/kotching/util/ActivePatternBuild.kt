package io.github.muqhc.kotching.util

import io.github.muqhc.kotching.ActivePattern
import io.github.muqhc.kotching.ActivePatternBuildContext

fun <T,L:Function<Any>> ActivePattern(init: ActivePatternBuildContext<T, L>.() -> Any) =
    object : ActivePattern<T, L> {
        val block = init
        override fun matching(input: T, lambda: L): Any =
            ActivePatternBuildContext(input, lambda).run(block)
    }