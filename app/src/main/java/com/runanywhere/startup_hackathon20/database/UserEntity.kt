package com.runanywhere.startup_hackathon20.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val username: String,
    val password: String,
    val createdAt: Long = System.currentTimeMillis(),
    val isLoggedIn: Boolean = false
)
