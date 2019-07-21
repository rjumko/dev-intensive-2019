package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String {
        TODO()
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        TODO()
    }

    enum class Status {
        NORMAL,
        WARNING,
        DANGER,
        CRITICAL,
    }

    enum class Question(val question: String, val answer: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")),
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")),
        MATERIAL("Из чего я сделан?", listOf("метал", "дерево", "metal", "iron", "wood")),
        BDAY("Когда меня создали?", listOf("2993")),
        SERIAL("Мой серийный номер?", listOf("2716057")),
        IDLE("На этом все, вопросов больше нет", listOf()),
    }

}