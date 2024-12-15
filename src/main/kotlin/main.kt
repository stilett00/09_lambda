fun  main() {
    val chatService = ChatService()

    chatService.addMessage(1, "Привет!")
    chatService.addMessage(1, "Как дела?")
    chatService.addMessage(1, "Пока не родила ;)")
    chatService.addMessage(2, "Это другой чат.")
    chatService.addMessage(2, "Да, это чат номер 2")

    println("*********************************************")
    chatService.showChat(1)
    chatService.showChat(2)
    chatService.showChat(3)

    chatService.deleteChat(2)

    chatService.addMessage(2, "Должно быть непрочитанное сообщение")

    chatService.deleteMessage(1, 2)
    println("*********************************************")
    chatService.showChat(1)

    println("*********************************************")
    val lastMessages = chatService.getLastMessages()
    lastMessages.forEach { println(it.textMessage) }

    println("*********************************************")
    val messages = chatService.getMessagesFromChat(1, 3)
    messages.forEach { println(it) }

    println("*********************************************")
    val allChats = chatService.getChatList()
    allChats.forEach { println(it) }
}