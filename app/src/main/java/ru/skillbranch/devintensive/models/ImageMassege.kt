package ru.skillbranch.devintensive.models

import java.util.*

class ImageMassege(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean,
    date: Date = Date(),
    var image: String?
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}