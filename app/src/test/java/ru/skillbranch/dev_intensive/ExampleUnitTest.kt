package ru.skillbranch.dev_intensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.dev_intensive.models.User
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user = User("1")
        val user2 = User("2", "John", "Wick")
        val user3 = User("3", "John", "Silverhand", null, lastVisit = Date(), isOnline = true)

        println("$user, $user2, $user3")
        user.printMe()
        user2.printMe()
        user3.printMe()
    }
}
