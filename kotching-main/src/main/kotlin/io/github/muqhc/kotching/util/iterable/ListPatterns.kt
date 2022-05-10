package io.github.muqhc.kotching.util.iterable

import io.github.muqhc.kotching.dsl.ActivePattern
import io.github.muqhc.kotching.dsl.require
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
        it.count() == 3
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
        it.count() == 4
    }

fun <T> listOf(
    w0: WildCard,
    w1: WildCard,
    w2: WildCard,
    w3: WildCard,
    w4: WildCard,
) =
    ActivePattern<Iterable<T>,(T,T,T,T,T)->Any> {
        export(
            input.elementAt(0),
            input.elementAt(1),
            input.elementAt(2),
            input.elementAt(3),
            input.elementAt(4),
        )
    }.require {
        it.count() == 5
    }

fun <T> listOf(
    w0: WildCard,
    w1: WildCard,
    w2: WildCard,
    w3: WildCard,
    w4: WildCard,
    w5: WildCard,
) =
    ActivePattern<Iterable<T>,(T,T,T,T,T,T)->Any> {
        export(
            input.elementAt(0),
            input.elementAt(1),
            input.elementAt(2),
            input.elementAt(3),
            input.elementAt(4),
            input.elementAt(5),
        )
    }.require {
        it.count() == 6
    }