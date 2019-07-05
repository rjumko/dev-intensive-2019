package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
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
    fun test_simple() {

        val x = 51
        print(x % 100)

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
        val messageDate = Date()
        val currDate = Date()
        messageDate.time = currDate.time
        assertEquals("только что", messageDate.humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("только что", messageDate.add(-1, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("только что", messageDate.add(1, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("несколько секунд назад", messageDate.add(-2, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через несколько секунд", messageDate.add(2, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("несколько секунд назад", messageDate.add(-45, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через несколько секунд", messageDate.add(45, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("минуту назад", messageDate.add(-46, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через минуту", messageDate.add(46, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("минуту назад", messageDate.add(-75, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через минуту", messageDate.add(75, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("1 минуту назад", messageDate.add(-76, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 1 минуту", messageDate.add(76, TimeUnits.SECOND).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 2 минуты", messageDate.add(2, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("3 минуты назад", messageDate.add(-3, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("45 минут назад", messageDate.add(-45, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 45 минут", messageDate.add(45, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("час назад", messageDate.add(-46, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через час", messageDate.add(46, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("час назад", messageDate.add(-75, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через час", messageDate.add(75, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("1 час назад", messageDate.add(-76, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 1 час", messageDate.add(76, TimeUnits.MINUTE).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("4 часа назад", messageDate.add(-4, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 4 часа", messageDate.add(4, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("6 часов назад", messageDate.add(-6, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 6 часов", messageDate.add(6, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("11 часов назад", messageDate.add(-11, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 11 часов", messageDate.add(11, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("22 часа назад", messageDate.add(-22, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 22 часа", messageDate.add(22, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("день назад", messageDate.add(-23, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через день", messageDate.add(23, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("день назад", messageDate.add(-26, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через день", messageDate.add(26, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("1 день назад", messageDate.add(-27, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 1 день", messageDate.add(27, TimeUnits.HOUR).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("12 дней назад", messageDate.add(-12, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 12 дней", messageDate.add(12, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("112 дней назад", messageDate.add(-112, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 112 дней", messageDate.add(112, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("360 дней назад", messageDate.add(-360, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("через 360 дней", messageDate.add(360, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более года назад", messageDate.add(-361, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более чем через год", messageDate.add(361, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более года назад", messageDate.add(-12345, TimeUnits.DAY).humanizeDiff(currDate))
        messageDate.time = currDate.time
        assertEquals("более чем через год", messageDate.add(12345, TimeUnits.DAY).humanizeDiff(currDate))
    }

    @Test
    fun builderTest(){
        val date = Date()

        val user1 = User(
            "5",
            "Никола",
            "Тесла",
            null,
            0,
            1000,
            date.add(-2, TimeUnits.DAY),
            false)

        val user2 = User.Builder().id("5")
            .firstName("Никола")
            .lastName("Тесла")
            .avatar(null)
            .rating(0)
            .respect(1000)
            .lastVisit(date.add(-2, TimeUnits.DAY))
            .isOnline(false)
            .build()

        val user3 = User.Builder().build()

        assertEquals(user1, user2)
        assertTrue(user3 is User)
        assertNotEquals(null, user3.id)
        assertEquals(null, user3.firstName)
        assertEquals(null, user3.lastName)
        assertEquals(null, user3.avatar)
        assertEquals(0, user3.rating)
        assertEquals(0, user3.respect)
        assertNotEquals(null, user3.lastVisit)
        assertFalse(user3.isOnline)
    }

}


