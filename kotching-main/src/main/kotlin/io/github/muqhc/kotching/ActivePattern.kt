package io.github.muqhc.kotching

interface ActivePattern<out T,out L : Function<Any>> {
    fun matching(input: @UnsafeVariance T, lambda: @UnsafeVariance L): Any

    fun checkRequire(input: @UnsafeVariance T): Boolean = true
}