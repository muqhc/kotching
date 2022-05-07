package io.github.muqhc.kotching.util

import io.github.muqhc.kotching.ActivePattern

@Suppress("UNCHECKED_CAST")
fun <T,R:Any,L:Function<R>> function(activePattern: ActivePattern<T, L>, block: L): (T) -> R =
    { input -> activePattern.matching(input,block) as R }