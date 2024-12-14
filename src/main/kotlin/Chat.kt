data class Chat (
    val chatId: Int,
    val messages: MutableList<Message> = mutableListOf()
    ) {

    private var nextMessageId: Int = 1

    fun resetMessages() {
        messages.clear()
        nextMessageId = 1
    }


    fun addMessage(textMessage: String) {
        messages.add(Message(nextMessageId++, textMessage))
    }

    fun getMessageCount(): Int {
        return messages.count()
    }

    fun hasUnreadMessages(): Boolean {
        return messages.any { !it.isRead }
    }

    fun getUnreadCount(): Int { // Добавлено
        return messages.count { !it.isRead }
    }

    fun markMessagesAsRead() {
        messages.forEach { it.isRead = true }
    }

    fun deleteMessage(messageId: Int): Boolean {
        return messages.removeIf { it.messageId == messageId }
    }
}
