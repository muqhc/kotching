package io.github.muqhc.kotching.util

import io.github.muqhc.kotching.ActivePattern
import io.github.muqhc.kotching.KotchingDsl
import io.github.muqhc.kotching.exception.NoMatchException

@Suppress("UNCHECKED_CAST")
@KotchingDsl
fun <T,R:Any,L:Function<R>> function(activePattern: ActivePattern<T, L>, block: L): (T) -> R =
    { input -> if (activePattern.checkRequire(input)) activePattern.matching(input, block) as R else throw NoMatchException("$input doesn't pattern match with $activePattern") }