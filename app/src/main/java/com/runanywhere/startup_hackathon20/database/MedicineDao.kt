package com.runanywhere.startup_hackathon20.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Query("SELECT * FROM medicines WHERE userId = :userId ORDER BY createdAt DESC")
    fun getMedicinesByUser(userId: Long): Flow<List<MedicineEntity>>

    @Query("SELECT * FROM medicines ORDER BY createdAt DESC")
    fun getAllMedicines(): Flow<List<MedicineEntity>>

    @Query("SELECT * FROM medicines WHERE id = :id")
    suspend fun getMedicineById(id: Long): MedicineEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicine(medicine: MedicineEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicines(medicines: List<MedicineEntity>)

    @Update
    suspend fun updateMedicine(medicine: MedicineEntity)

    @Delete
    suspend fun deleteMedicine(medicine: MedicineEntity)

    @Query("DELETE FROM medicines WHERE userId = :userId")
    suspend fun deleteAllMedicinesByUser(userId: Long)

    @Query("DELETE FROM medicines")
    suspend fun deleteAllMedicines()

    @Query("SELECT COUNT(*) FROM medicines WHERE userId = :userId")
    suspend fun getMedicineCountByUser(userId: Long): Int

    @Query("SELECT COUNT(*) FROM medicines")
    suspend fun getMedicineCount(): Int
}
