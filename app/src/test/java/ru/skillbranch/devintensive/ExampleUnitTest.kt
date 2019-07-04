package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.models.User

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
       // val user = User("1")
       // val user2 = User("2", "John", "Doe")
      //  val user3 = User("3", "John", "Silverhand", null, lastVisit = Date(), isOnline = true)

       // println("$user, $user2, $user3")
       // user.printMe()
       // user2.printMe()
      //  user3.printMe()
    }
    @Test
    fun test_factory() {
        User.makeUser("Jonn psina")
        User.makeUser("Jonn")
        User.makeUser("")
        User.makeUser(null)
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John psin1")
        val user2 = user.copy(firstName = "Edik", lastName = "Britt")

        print("$user\n$user2")
        println()
        print("${user.hashCode()}\n${user2.hashCode()}")
    }
}
