package ru.skillbranch.dev_intensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.dev_intensive.models.User

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
        val user2 = User("2")
        val user3 = User("3")

        println("$user, $user2, $user3")
    }
}
