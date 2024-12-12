fun  main() {
    val chatService = ChatService()

    chatService.addMessage(1, "Привет!")
    chatService.addMessage(1, "Как дела?")
    chatService.addMessage(1, "Пока не родила ;)")
    chatService.addMessage(2, "Это другой чат.")
    chatService.addMessage(2, "Да, это чат номер 2")

    chatService.showChat(1)
    chatService.showChat(2)
    chatService.showChat(3)

    chatService.deleteChat(2)

    chatService.addMessage(2, "Должно быть непрочитанное сообщение")

}