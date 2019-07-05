package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(currDate: Date = Date()): String {

    fun declension(number: Int, units: TimeUnits): String {

        return when (units) {
            TimeUnits.HOUR -> when (number) {
                1 -> "час"
                2, 3, 4 -> "часа"
                else -> "часов"
            }
            TimeUnits.MINUTE -> when (number) {
                2, 3, 4 -> "минуты"
                else -> "минут"
            }
            TimeUnits.DAY -> when (number) {
                1 -> "день"
                2, 3, 4 -> "дня"
                else -> "дней"
            }
            else -> ""
        }

    }

    val currTime = currDate.time
    val time = this.time
    val diffTime = (currTime - time) / 1000

    return when {
        diffTime < 2 -> "только что"
        diffTime < 46 -> "несколько секунд назад"
        diffTime < 76 -> "минуту назад"
        diffTime < 2760 -> "${diffTime / 60} ${declension((diffTime / 60).toInt(), TimeUnits.MINUTE)} назад"
        diffTime < 4560 -> "час назад"
        diffTime < 82800 -> "${diffTime / 3600} ${declension((diffTime / 3600).toInt(), TimeUnits.HOUR)} назад"
        diffTime < 97200 -> "день назад"
        diffTime < 31190400 -> "${diffTime / 3600 / 24} ${declension((diffTime / 3600 / 24).toInt(), TimeUnits.DAY)} назад"
        else -> "более года назад"
    }
    //TODO("need implement")

    /* 0с - 1с "только что"                  0 - 1
     1с - 45с "несколько секунд назад"       2 - 45
     45с - 75с "минуту назад"                46 - 75
     75с - 45мин "N минут назад"             76 - 2700
     45мин - 75мин "час назад"               2760 - 4500
     75мин 22ч "N часов назад"               4560 - 79200
     22ч - 26ч "день назад"                  82800 - 93600
     26ч - 360д "N дней назад"               97200 - 31104000
     >360д "более года назад"                31190400*/

}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

