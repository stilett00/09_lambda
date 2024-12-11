fun  main() {
    val chatService = ChatService()

    chatService.addMessage(1, "Hello")
    chatService.addMessage(1, "How are u?")

    chatService.showChat(1)
}