package com.example.peoplelist.presentation

fun <T> List<T>.replace(old: T, transform: T.() -> T): List<T> {
    return map { element -> if (element == old) old.transform() else element }
}