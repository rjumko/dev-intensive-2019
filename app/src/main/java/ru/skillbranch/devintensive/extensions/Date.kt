package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

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

    val currTime = currDate.time
    val time = this.time
    val sign = (currTime - time) < 0
    val diffTime = abs(((currTime - time) / 1000).toInt())
    val prefix = if (sign) "через " else ""
    val postfix = if (!sign) " назад" else ""

    return when {
        diffTime < 2 -> "только что"
        diffTime < 46 -> "${prefix}несколько секунд$postfix"
        diffTime < 76 -> "${prefix}минуту$postfix"
        diffTime < 2760 -> "$prefix${TimeUnits.MINUTE.plural((diffTime / 60))}$postfix"
        diffTime < 4560 -> "${prefix}час$postfix"
        diffTime < 82800 -> "$prefix${TimeUnits.HOUR.plural((diffTime / 3600))}$postfix"
        diffTime < 97200 -> "${prefix}день$postfix"
        diffTime < 31190400 -> "$prefix${TimeUnits.DAY.plural((diffTime / 3600 / 24))}$postfix"
        else -> if (!sign) "более года назад" else "более чем через год"
    }

}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Int): String {
            return "$value " + when (value % 100) {
                10, 11, 12, 13, 14 -> "секунд"
                else -> when (value % 10) {
                    1 -> "секунду"
                    2, 3, 4 -> "секунды"
                    else -> "секунд"
                }
            }
        }
    },
    MINUTE {
        override fun plural(value: Int): String {
            return "$value " + when (value % 100) {
                10, 11, 12, 13, 14 -> "минут"
                else -> when (value % 10) {
                    1 -> "минуту"
                    2, 3, 4 -> "минуты"
                    else -> "минут"
                }
            }
        }
    },
    HOUR {
        override fun plural(value: Int): String {
            return "$value " + when (value % 100) {
                10, 11, 12, 13, 14 -> "часов"
                else -> when (value % 10) {
                    1 -> "час"
                    2, 3, 4 -> "часа"
                    else -> "часов"
                }
            }
        }
    },
    DAY {
        override fun plural(value: Int): String {
            return "$value " + when (value % 100) {
                10, 11, 12, 13, 14 -> "дней"
                else -> when (value % 10) {
                    1 -> "день"
                    2, 3, 4 -> "дня"
                    else -> "дней"
                }
            }
        }
    };

    abstract fun plural(value: Int): String

}

