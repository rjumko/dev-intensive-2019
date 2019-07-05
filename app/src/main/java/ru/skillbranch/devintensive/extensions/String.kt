package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {

    val string = this.trim()

    return string.take(value).trimEnd() + if(string.length>value) "..." else ""

}