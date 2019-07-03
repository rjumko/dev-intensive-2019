package ru.skillbranch.dev_intensive.models

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
                "${if(lastName === "Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n" +
        "${getIntro()}")
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

}
