package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import ru.skillbranch.devintensive.utils.Utils.parseFullName
import java.util.*
import kotlin.math.abs

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

    @Test
    fun test_to_initials() {
        assertEquals("JD", Utils.toInitials("john", "doe"))
        assertEquals("J", Utils.toInitials("John", null))
        assertEquals(null, Utils.toInitials(null, null))
        assertEquals(null, Utils.toInitials(" ", ""))

        /* additional tests */
        assertEquals(null, Utils.toInitials(" ", null))
        assertEquals(null, Utils.toInitials(null, ""))
        assertEquals("T", Utils.toInitials(null, "  tommy"))
        assertEquals("ST", Utils.toInitials("  samuel  ", "  tommy"))
        assertEquals("J", Utils.toInitials(null, "John"))
    }

    @Test
    fun transliteration_test() {
        /* skillBranch tests */

        println(Utils.transliteration("Женя Стереотипов"))
        for ( i in 'А' .. 'Я') print(i+" ")
        assertEquals("Zhenya Stereotipov", Utils.transliteration("Женя Стереотипов"))
        assertEquals("Amazing_Petr", Utils.transliteration("Amazing Петр","_"))

        /* additional tests */
        assertEquals("iVan     Stereotizhov", Utils.transliteration(" иВан     Стереотижов "))
        assertEquals("Amazing_PeZhr", Utils.transliteration(" Amazing ПеЖр ", "_"))
        assertEquals("aAbBvVgGdDeEeEzhZhzZiIiIkKlL", Utils.transliteration("аАбБвВгГдДеЕёЁжЖзЗиИйЙкКлЛ"))
        assertEquals("mMnNoOpPrRsStTuUfFhHcCshShsh'Sh'", Utils.transliteration("мМнНоОпПрРсСтТуУфФхХцЦшШщЩ"))
        assertEquals("eEyuYuyaYa", Utils.transliteration("ъЪьЬэЭюЮяЯ"))
        assertEquals("123|!,^-=+><|english", Utils.transliteration("123 !,^-=+>< english", "|"))
        assertEquals("Zhizha ZhiZhnaYa", Utils.transliteration("Жижа ЖиЖнаЯ"))
        assertEquals("Sobaka is a dog", Utils.transliteration("Собака dog", " is a "))
    }

    @Test
    fun humanizeDiffTest() {

        val date = Date()
        val date2 = Date().add(100, TimeUnits.DAY)
        println(date)
        //println(date.add(6, TimeUnits.SECOND))
        println(date2.time)
        println(abs(-555))
       // println(date.add(6, TimeUnits.SECOND).time.toString())
        println((date2.time - date.time)/1000)

        println(date.humanizeDiff(date2))

    }

}


