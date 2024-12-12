class ChatService {
    private var chats: MutableList<Chat> = mutableListOf()

    private fun findChatById(chatId: Int): Chat? {
        return chats.find { it.chatId == chatId }
    }

    fun resetService() {
        chats.clear()
    }


    fun addMessage(chatId: Int, textMessage: String) {
        val chat = findChatById(chatId)
        if (chat != null) {
            chat.addMessage(textMessage)
            println("Сообщение добавлено в чат $chatId")
        } else {
            val newChat = Chat(chatId)
            newChat.addMessage(textMessage)
            chats.add(newChat)
            println("Чат с ID $chatId создан, сообщение добавлено")
        }
    }

    fun deleteChat(chatId: Int) {
        val isRemoved = chats.removeIf { it.chatId == chatId }
        if (isRemoved) {
            println("Чат с ID $chatId удалён.")
        } else {
            println("Чат с ID $chatId не найден.")
        }
    }


    fun showChat(chatId: Int) {
        val chat = findChatById(chatId)
        if (chat != null) {
            if (chat.messages.isEmpty()) {
                println("Чат c ID ${chat.chatId} пуст.")
            } else {
                println("Сообщения чата ID: ${chat.chatId}, всего ${chat.getMessageCount()} шт.")
                chat.messages.forEach { message ->
                    val readStatus = if (message.isRead) "Прочитано" else "Непрочитано"
                    println("${message.messageId}: ${message.textMessage} ($readStatus)")
                }
                chat.markMessagesAsRead()
            }
        } else {
            println("Чат c ID $chatId не найден")
        }
    }

    fun getChatListWithUnreadMessages(): List<Chat> {
        return chats.filter { it.hasUnreadMessages() }
    }

    fun getChatList(): List<Chat> {
        return chats
    }
}
