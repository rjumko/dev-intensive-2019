package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {

    val string = this.trim()

    return string.take(value).trimEnd() + if (string.length > value) "..." else ""

}

fun String.stripHtml(): String {

    return this.replace("(&[a-zA-Z]*?;)|(<.*?>)|(&#\\d+?;)".toRegex(), "").replace("\\u0020{2,}".toRegex(), " ")

}
