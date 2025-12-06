package com.runanywhere.startup_hackathon20.database

import kotlinx.coroutines.flow.Flow

class MedicineRepository(private val medicineDao: MedicineDao) {

    val allMedicines: Flow<List<MedicineEntity>> = medicineDao.getAllMedicines()

    fun getMedicinesByUser(userId: Long): Flow<List<MedicineEntity>> {
        return medicineDao.getMedicinesByUser(userId)
    }

    suspend fun insertMedicine(medicine: MedicineEntity): Long {
        return medicineDao.insertMedicine(medicine)
    }

    suspend fun insertMedicines(medicines: List<MedicineEntity>) {
        medicineDao.insertMedicines(medicines)
    }

    suspend fun updateMedicine(medicine: MedicineEntity) {
        medicineDao.updateMedicine(medicine)
    }

    suspend fun deleteMedicine(medicine: MedicineEntity) {
        medicineDao.deleteMedicine(medicine)
    }

    suspend fun deleteAllMedicinesByUser(userId: Long) {
        medicineDao.deleteAllMedicinesByUser(userId)
    }

    suspend fun deleteAllMedicines() {
        medicineDao.deleteAllMedicines()
    }

    suspend fun getMedicineById(id: Long): MedicineEntity? {
        return medicineDao.getMedicineById(id)
    }

    suspend fun getMedicineCountByUser(userId: Long): Int {
        return medicineDao.getMedicineCountByUser(userId)
    }

    suspend fun getMedicineCount(): Int {
        return medicineDao.getMedicineCount()
    }
}
