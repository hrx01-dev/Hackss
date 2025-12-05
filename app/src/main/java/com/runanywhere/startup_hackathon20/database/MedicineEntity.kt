package com.runanywhere.startup_hackathon20.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val dosage: String,
    val frequency: String,
    val time: String,
    val duration: String,
    val quantity: String,
    val instructions: String,
    val createdAt: Long = System.currentTimeMillis()
)
