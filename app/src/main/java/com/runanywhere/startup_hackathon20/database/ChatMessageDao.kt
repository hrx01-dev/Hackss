package com.runanywhere.startup_hackathon20.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatMessageDao {

    @Query("SELECT * FROM chat_messages WHERE userId = :userId ORDER BY timestamp ASC")
    fun getMessagesByUser(userId: Long): Flow<List<ChatMessageEntity>>

    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<ChatMessageEntity>>

    @Query("SELECT * FROM (SELECT * FROM chat_messages WHERE userId = :userId ORDER BY timestamp DESC LIMIT 20) ORDER BY timestamp ASC")
    fun getRecent20MessagesByUser(userId: Long): Flow<List<ChatMessageEntity>>

    @Query("SELECT * FROM (SELECT * FROM chat_messages ORDER BY timestamp DESC LIMIT 20) ORDER BY timestamp ASC")
    fun getRecent20Messages(): Flow<List<ChatMessageEntity>>

    @Query("SELECT * FROM chat_messages WHERE userId = :userId ORDER BY timestamp ASC LIMIT 20")
    suspend fun getRecent20MessagesSyncByUser(userId: Long): List<ChatMessageEntity>

    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC LIMIT 20")
    suspend fun getRecent20MessagesSync(): List<ChatMessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessageEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<ChatMessageEntity>)

    @Query("DELETE FROM chat_messages WHERE userId = :userId")
    suspend fun deleteAllMessagesByUser(userId: Long)

    @Query("DELETE FROM chat_messages")
    suspend fun deleteAllMessages()

    @Query("SELECT COUNT(*) FROM chat_messages WHERE userId = :userId")
    suspend fun getMessageCountByUser(userId: Long): Int

    @Query("SELECT COUNT(*) FROM chat_messages")
    suspend fun getMessageCount(): Int

    @Query(
        """
        DELETE FROM chat_messages 
        WHERE userId = :userId AND id NOT IN (
            SELECT id FROM chat_messages 
            WHERE userId = :userId
            ORDER BY timestamp DESC 
            LIMIT 20
        )
    """
    )
    suspend fun deleteOldMessagesByUser(userId: Long)

    @Query(
        """
        DELETE FROM chat_messages 
        WHERE id NOT IN (
            SELECT id FROM chat_messages 
            ORDER BY timestamp DESC 
            LIMIT 20
        )
    """
    )
    suspend fun deleteOldMessages()

    @Query("DELETE FROM chat_messages WHERE id = :messageId")
    suspend fun deleteMessageById(messageId: Long)
}
