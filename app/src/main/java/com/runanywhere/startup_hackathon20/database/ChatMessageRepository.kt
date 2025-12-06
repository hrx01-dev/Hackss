package com.runanywhere.startup_hackathon20.database

import kotlinx.coroutines.flow.Flow

class ChatMessageRepository(private val chatMessageDao: ChatMessageDao) {

    val allMessages: Flow<List<ChatMessageEntity>> = chatMessageDao.getAllMessages()

    val recent20Messages: Flow<List<ChatMessageEntity>> = chatMessageDao.getRecent20Messages()

    fun getMessagesByUser(userId: Long): Flow<List<ChatMessageEntity>> {
        return chatMessageDao.getMessagesByUser(userId)
    }

    fun getRecent20MessagesByUser(userId: Long): Flow<List<ChatMessageEntity>> {
        return chatMessageDao.getRecent20MessagesByUser(userId)
    }

    suspend fun insertMessage(message: ChatMessageEntity, userId: Long): Long {
        val id = chatMessageDao.insertMessage(message)
        // Keep only recent 20 messages per user
        cleanupOldMessages(userId)
        return id
    }

    suspend fun insertMessages(messages: List<ChatMessageEntity>, userId: Long) {
        chatMessageDao.insertMessages(messages)
        // Keep only recent 20 messages per user
        cleanupOldMessages(userId)
    }

    suspend fun deleteAllMessagesByUser(userId: Long) {
        chatMessageDao.deleteAllMessagesByUser(userId)
    }

    suspend fun deleteAllMessages() {
        chatMessageDao.deleteAllMessages()
    }

    suspend fun getMessageCountByUser(userId: Long): Int {
        return chatMessageDao.getMessageCountByUser(userId)
    }

    suspend fun getMessageCount(): Int {
        return chatMessageDao.getMessageCount()
    }

    suspend fun getRecent20MessagesSyncByUser(userId: Long): List<ChatMessageEntity> {
        return chatMessageDao.getRecent20MessagesSyncByUser(userId)
    }

    suspend fun getRecent20MessagesSync(): List<ChatMessageEntity> {
        return chatMessageDao.getRecent20MessagesSync()
    }

    private suspend fun cleanupOldMessages(userId: Long) {
        val count = chatMessageDao.getMessageCountByUser(userId)
        if (count > 20) {
            chatMessageDao.deleteOldMessagesByUser(userId)
        }
    }
}
