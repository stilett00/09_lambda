import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ChatServiceTest {

    private lateinit var chatService: ChatService

    @Before
    fun setUp() {
        chatService = ChatService()
    }

    @Test
    fun testAddMessageToNewChat() {
        chatService.addMessage(1, "Hello, Chat 1!")

        // Проверяем, что чат был добавлен и сообщение в нем
        val chat = chatService.getChatList().firstOrNull { it.chatId == 1 }
        assertEquals(1, chat?.getMessageCount())
        assertEquals("Hello, Chat 1!", chat?.messages?.first()?.textMessage)
    }

    @Test
    fun testAddMessageToExistingChat() {
        chatService.addMessage(1, "Hello, Chat 1!")
        chatService.addMessage(1, "Another message in Chat 1!")

        // Проверяем, что сообщение добавилось в существующий чат
        val chat = chatService.getChatList().firstOrNull { it.chatId == 1 }
        assertEquals(2, chat?.getMessageCount())
        assertEquals("Another message in Chat 1!", chat?.messages?.last()?.textMessage)
    }

    @Test
    fun testUnreadMessagesCount() {
        chatService.addMessage(1, "Message 1") // Непрочитанное сообщение по умолчанию
        chatService.addMessage(1, "Message 2")

        val chat = chatService.getChatList().firstOrNull { it.chatId == 1 }

        // Проверяем, что количество непрочитанных сообщений правильно
        assertEquals(2, chat?.getUnreadCount())
    }

    @Test
    fun testMarkMessagesAsRead() {
        chatService.addMessage(1, "Message 1")
        chatService.addMessage(1, "Message 2")

        // Помечаем сообщения как прочитанные
        val chat = chatService.getChatList().firstOrNull { it.chatId == 1 }
        chat?.markMessagesAsRead()

        // Проверяем, что все сообщения помечены как прочитанные
        assertTrue(chat?.messages?.all { it.isRead } == true)
    }

    @Test
    fun testDeleteChat() {
        chatService.addMessage(1, "Message in Chat 1")
        chatService.addMessage(2, "Message in Chat 2")

        // Удаляем чат 1
        chatService.deleteChat(1)

        // Проверяем, что чат 1 был удален
        val chatList = chatService.getChatList()
        val deletedChat = chatList.firstOrNull { it.chatId == 1 }
        assertEquals(null, deletedChat)  // Чат должен быть удален
    }

    @Test
    fun testGetChatListWithUnreadMessages() {
        chatService.addMessage(1, "Message 1") // Чат 1 с непрочитанным сообщением
        chatService.addMessage(2, "Message in Chat 2") // Чат 2 с непрочитанным сообщением

        // Убираем прочтение сообщений в чате 1
        val chat1 = chatService.getChatList().firstOrNull { it.chatId == 1 }
        chat1?.markMessagesAsRead() // Помечаем все сообщения в чате 1 как прочитанные

        // Проверяем, что выводим только чаты с непрочитанными сообщениями
        val chatsWithUnread = chatService.getChatListWithUnreadMessages()
        assertEquals(1, chatsWithUnread.size)  // Только чат 2 должен остаться
        assertEquals(1, chatsWithUnread[0].getUnreadCount())  // Чат 2 должен содержать 1 непрочитанное сообщение
    }

    @Test
    fun testGetChatList() {
        chatService.addMessage(1, "Message 1")
        chatService.addMessage(2, "Message 2")

        val chatList = chatService.getChatList()

        // Проверяем, что чаты корректно добавлены в список
        assertEquals(2, chatList.size)  // Должно быть два чата
        assertEquals(1, chatList[0].getMessageCount())  // В каждом чате одно сообщение
        assertEquals(1, chatList[1].getMessageCount())  // В каждом чате одно сообщение
    }
}
