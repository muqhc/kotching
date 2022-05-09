package io.github.muqhc.kotching.test

import io.github.muqhc.kotching.util.*

fun <T> HeadTail() =
    ActivePattern<Iterable<T>,(T,Iterable<T>)->Any> {
        export(
            input.first(),
            input.drop(1)
        )
    }.require {
        it.count() >= 1
    }

fun <T> listOf() =
    ActivePattern<Iterable<T>,()->Any> {
        export()
    }.require {
        it.count() == 0
    }

fun <T> listOf(w: WildCard) =
    ActivePattern<Iterable<T>,(T)->Any> {
        export(input.first())
    }.require {
        it.count() == 1
    }

fun <T> listOf(
    w0: WildCard,
    w1: WildCard,
) =
    ActivePattern<Iterable<T>,(T,T)->Any> {
        export(
            input.elementAt(0),
            input.elementAt(1),
        )
    }.require {
        it.count() == 2
    }

fun <T> listOf(
    w0: WildCard,
    w1: WildCard,
    w2: WildCard,
) =
    ActivePattern<Iterable<T>,(T,T,T)->Any> {
        export(
            input.elementAt(0),
            input.elementAt(1),
            input.elementAt(2),
        )
    }.require {
        it.count() == 2
    }

fun <T> listOf(
    w0: WildCard,
    w1: WildCard,
    w2: WildCard,
    w3: WildCard,
) =
    ActivePattern<Iterable<T>,(T,T,T,T)->Any> {
        export(
            input.elementAt(0),
            input.elementAt(1),
            input.elementAt(2),
            input.elementAt(3),
        )
    }.require {
        it.count() == 2
    }