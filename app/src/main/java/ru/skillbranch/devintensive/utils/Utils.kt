package ru.skillbranch.devintensive.utils

object Utils {

    val dictionary = mapOf("а" to "a", "б" to "b", "в" to "v",
        "г" to "g", "д" to "d", "е" to "e", "ё" to "e", "ж" to "zh",
        "з" to "z", "и" to "i", "й" to "i", "к" to "k", "л" to "l",
        "м" to "m", "н" to "n", "о" to "o", "п" to "p", "р" to "r",
        "с" to "s", "т" to "t", "у" to "u", "ф" to "f", "х" to "h",
        "ц" to "c", "ч" to "ch", "ш" to "sh", "щ" to "sh'", "ъ" to "",
        "ы" to "i", "ь" to "", "э" to "e", "ю" to "yu", "я" to "ya")

    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val parts: List<String>? = fullName?.trimStart()?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        if (firstName == "" || firstName == " ") firstName = null
        if (lastName == "" || lastName == " ") lastName = null

        //return Pair(firstName, lastName)
        return firstName to lastName

    }

    fun transliteration(payload: String, divider: String = " "): String {

        val result = (payload.trim().map { when(it) {
            in 'а' .. 'я' -> dictionary[it.toString()]
            in 'А' .. 'Я' -> dictionary[it.toString().toLowerCase()]?.capitalize()
            'Ё' -> 'E'
            'ё' -> 'e'
            ' ' -> divider
            else -> it
        }}).joinToString(separator="")

        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val tmpStr = (firstName?.trim()?.toUpperCase()?.getOrNull(0) ?: "").toString()
        val tmpStr2 = (lastName?.trim()?.toUpperCase()?.getOrNull(0) ?: "").toString()
        return if (tmpStr == "" && tmpStr2 == "") null
        else tmpStr + tmpStr2

    }

}

