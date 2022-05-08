package io.github.muqhc.kotching

class ActivePatternBuildContext<out T,out L : Function<Any>>(val input: T, lambda: L) {
    val export: L = lambda
}