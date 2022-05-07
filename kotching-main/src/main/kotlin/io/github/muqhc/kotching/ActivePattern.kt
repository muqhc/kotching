package io.github.muqhc.kotching

fun interface ActivePattern<out T,out L : Function<Any>> {
    fun matching(input: @UnsafeVariance T, lambda: @UnsafeVariance L): Any
}