package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {


        return if (question.validation(answer).second) {
            if (question.answer.contains(answer)) {
                question = question.nextQuestion()
                "Отлично - ты справился!\n${question.question}" to status.color
            } else {
                if (status == Status.CRITICAL) {
                    status = Status.NORMAL
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                } else {
                    status = status.nextStatus()
                    "Это неправильный ответ!\n${question.question}" to status.color
                }
            }
        } else {
            question.validation(answer).first to status.color
        }
    }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {

            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }

        }

    }

    enum class Question(val question: String, val answer: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun validation(answer: String): Pair<String, Boolean> {
                return if (answer.first() != null) {
                    if (answer.first().isLowerCase()) "Имя должно начинаться с заглавной буквы\nКак меня зовут?" to false
                    else "" to true
                } else "" to true
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun validation(answer: String): Pair<String, Boolean> {
                return if (!answer.first().isLowerCase()) "Профессия должна начинаться со строчной буквы\nНазови мою профессию?" to false
                else "" to true
            }
        },
        MATERIAL("Из чего я сделан?", listOf("метал", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun validation(answer: String): Pair<String, Boolean> {
                return if (!answer.contains("\\d+".toRegex())) "Материал не должен содержать цифр\n$question" to false
                else "" to true
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun validation(answer: String): Pair<String, Boolean> {
                return if (!answer.first().isLowerCase()) "Профессия должна начинаться со строчной буквы\nНазови мою профессию?" to false
                else "" to true
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun validation(answer: String): Pair<String, Boolean> {
                return if (!answer.first().isLowerCase()) "Профессия должна начинаться со строчной буквы\nНазови мою профессию?" to false
                else "" to true
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun validation(answer: String): Pair<String, Boolean> {
                return if (!answer.first().isLowerCase()) "Профессия должна начинаться со строчной буквы\nНазови мою профессию?" to false
                else "" to true
            }
        };

        abstract fun nextQuestion(): Question
        abstract fun validation(answer: String): Pair<String, Boolean>
    }

}