package com.runanywhere.startup_hackathon20.database

import kotlinx.coroutines.flow.Flow

class ChatMessageRepository(private val chatMessageDao: ChatMessageDao) {

    val allMessages: Flow<List<ChatMessageEntity>> = chatMessageDao.getAllMessages()

    val recent20Messages: Flow<List<ChatMessageEntity>> = chatMessageDao.getRecent20Messages()

    suspend fun insertMessage(message: ChatMessageEntity): Long {
        val id = chatMessageDao.insertMessage(message)
        // Keep only recent 20 messages
        cleanupOldMessages()
        return id
    }

    suspend fun insertMessages(messages: List<ChatMessageEntity>) {
        chatMessageDao.insertMessages(messages)
        // Keep only recent 20 messages
        cleanupOldMessages()
    }

    suspend fun deleteAllMessages() {
        chatMessageDao.deleteAllMessages()
    }

    suspend fun getMessageCount(): Int {
        return chatMessageDao.getMessageCount()
    }

    suspend fun getRecent20MessagesSync(): List<ChatMessageEntity> {
        return chatMessageDao.getRecent20MessagesSync()
    }

    private suspend fun cleanupOldMessages() {
        val count = chatMessageDao.getMessageCount()
        if (count > 20) {
            chatMessageDao.deleteOldMessages()
        }
    }
}
