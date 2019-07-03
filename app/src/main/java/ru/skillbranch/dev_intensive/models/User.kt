package ru.skillbranch.dev_intensive.models

import ru.skillbranch.dev_intensive.utils.Utils
import java.util.*

class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
){
    var introBit: String =""

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null)

    constructor(id: String) : this(id, "John", "Doe $id")

    init {
        println("It's Allive!!!\n" +
                "${if(lastName === "Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n")
    }

    private fun getIntro()="""
        tu tu tu tururur !!!!
        ru tufu !!!
        ${"\n"}
        $firstName $lastName
    """.trimIndent()

    fun printMe() = println("""
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnline: $isOnline

        """.trimIndent())

    companion object Factory {
        private var lastId:Int = -1
        fun makeUser(fullName:String?) : User{
            lastId++

            var (firstName, lastName) = Utils.parseFullName(fullName)

            if (firstName == null || firstName=="") firstName = "John"
            if (lastName == null || lastName=="") lastName = "Doe $lastId"

            return User(id= "$lastId", firstName = firstName, lastName = lastName)
        }
    }

}
