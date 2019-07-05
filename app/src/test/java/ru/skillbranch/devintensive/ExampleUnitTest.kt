package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils.parseFullName
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
        val user2 = user.copy(firstName = "Edik", lastName = "Britt", lastVisit = Date().add(3, TimeUnits.DAY))

        //print("$user\n$user2")
        //println()
        print("${user.lastVisit?.format("HH:mm:ss dd.MM.yy")}\n${user2.lastVisit?.format("HH:mm:ss dd.MM.yy")}")
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Алексеев Роман")
        val userView = user.toUserView()

        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Алексеев Роман")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image", isIncoming = true)

        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())
    }

    @Test
    fun test_parse_full_name() {
        println(parseFullName("asd asd"))
        println(parseFullName(null))
        println(parseFullName(""))
        println(parseFullName(" "))
        println(parseFullName("John"))
        println(parseFullName("     "))
        println(parseFullName("null"))
        println(parseFullName("John      "))
        println(parseFullName(" John     "))
    }
}


