class ChatService {
    private var chats: MutableList<Chat> = mutableListOf()

    private fun findChatById(chatId: Int): Chat? {
        return chats.find { it.chatId == chatId }
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

    fun getLastMessages(): List<Message> {
        return chats
            .asSequence()
            .mapNotNull { chat -> chat.messages.lastOrNull() }
            .toList()
    }

//    fun getLastMessages(): List<String> {
//        return chats.map { chat ->
//            if (chat.messages.isNotEmpty()) {
//                val lastMessage = chat.messages.last()
//                "Чат ${chat.chatId}: ${lastMessage.textMessage}"
//            } else {
//                "Чат ${chat.chatId}: нет сообщений"
//            }
//        }
//    }


    fun getMessagesFromChat(chatId: Int, messageCount: Int): List<Message> {
        val chat = findChatById(chatId)
        if (chat != null) {
            if (chat.messages.isEmpty()) {
                println("Чат c ID $chatId пуст.")
                return emptyList()
            }

            // Используем drop для пропуска первых сообщений, а затем берем нужное количество последних
            val messagesToReturn = chat.messages.asSequence()
                .drop(maxOf(0, chat.messages.size - messageCount))  // Пропускаем сообщения, оставляя последние
                .onEach { it.isRead = true }

            return messagesToReturn.toList()
        } else {
            println("Чат c ID $chatId не найден.")
            return emptyList()
        }
    }

//    fun getMessagesFromChat(chatId: Int, messageCount: Int): List<String> {
//        val chat = findChatById(chatId)
//        if (chat != null) {
//            if (chat.messages.isEmpty()) {
//                println("Чат c ID $chatId пуст.")
//                return emptyList()
//            }
//
//            val messagesToReturn = chat.messages.takeLast(messageCount)
//
//
//            messagesToReturn.forEach { it.isRead = true }
//            return messagesToReturn.map { "${it.messageId}: ${it.textMessage}" }
//        } else {
//            println("Чат c ID $chatId не найден.")
//            return emptyList()
//        }
//    }


    fun deleteMessage(chatId: Int, messageId: Int) {
        val chat = findChatById(chatId)
        if (chat != null) {
            val isRemoved = chat.deleteMessage(messageId)
            if (isRemoved) {
                println("Сообщение с ID $messageId удалено из чата $chatId.")
            } else {
                println("Сообщение с ID $messageId не найдено в чате $chatId.")
            }
        } else {
            println("Чат с ID $chatId не найден.")
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
