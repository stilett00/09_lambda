class ChatService {
    val chats: MutableList<Chat> = mutableListOf()

    fun addMessage(chatId: Int, textMessage: String) {
        val chat = chats.find { it.chatId == chatId }
        if (chat != null) {
            chat.messages.add(Message(textMessage))
            println("Сообщение добавлено в чат $chatId")
        } else {
            val newChat = Chat(chatId)
            newChat.messages.add(Message(textMessage))
            chats.add(newChat)
            println("Чат с ID $chatId создан, сообщение добавлено")
        }
    }

    fun showChat(chatId: Int) {
        val chat = chats.find { it.chatId == chatId }
        if (chat != null) {
            println(chat)
        } else {
            println("Чат не найден")
        }
    }
}