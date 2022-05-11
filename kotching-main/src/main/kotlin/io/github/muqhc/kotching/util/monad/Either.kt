package io.github.muqhc.kotching.util.monad

import io.github.muqhc.kotching.dsl.ActivePattern
import io.github.muqhc.kotching.dsl.require
import io.github.muqhc.kotching.util.WildCard

class Either<L,R> {
    internal var left: L? = null
    internal var right: R? = null

    constructor(left: L, w: WildCard) { this.left = left }
    constructor(w: WildCard, right: R) { this.right = right }

    companion object {
        fun <L,R> Left() = ActivePattern<Either<L,R>,(L)->Any> {
            export(input.left!!)
        }.require { it.left != null }

        fun <L,R> Right() = ActivePattern<Either<L,R>,(R)->Any> {
            export(input.right!!)
        }.require { it.right != null }
    }
}