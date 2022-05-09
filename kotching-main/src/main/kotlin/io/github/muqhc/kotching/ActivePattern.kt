package io.github.muqhc.kotching

import io.github.muqhc.kotching.util.Exported

interface ActivePattern<out T,out L : Function<Any>> {
    fun matching(input: @UnsafeVariance T, lambda: @UnsafeVariance L): Any

    fun checkRequire(input: @UnsafeVariance T): Boolean = true
}