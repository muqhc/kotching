package io.github.muqhc.kotching

@KotchingDsl
class PatternMatchingContext<T>(val input: T) {
    internal var isEnd = false
    internal var result: Any? = null

    @KotchingDsl
    fun <L : Function<Any>> case(activePattern: ActivePattern<T,L>, running: L) {
        if (isEnd) return
        try {
            if (activePattern.checkRequire(input)) {
                result = activePattern.matching(
                    input, running
                )
                isEnd = true
            }
        } catch (e: ClassCastException) {  }
    }

    @Suppress("UNCHECKED_CAST")
    @KotchingDsl
    fun <T> case(running: (T) -> Any) {
        if (isEnd) return
        (input as? T)?.let {
            result = running(it)
            isEnd = true
        }
    }

    @KotchingDsl
    fun else_(running: (T) -> Any) {
        if (isEnd) return
        result = running(input)
        isEnd = true
    }
}