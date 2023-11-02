package com.example.customappproject

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class DatabaseRepository(private val catDao: CatDao, private val weightLogDao: WeightLogDao, private val foodLogDao: FoodLogDao, private val foodTypeDao: FoodTypeDao) {

    val allCats: Flow<List<Cat>> = catDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCat(cat: Cat) {
        catDao.insert(cat)
    }


    @WorkerThread
    suspend fun updateCat(cat: Cat) {
        catDao.update(cat)
    }
    //val weightLogsForCat: Flow<List<Cat>> = catDao.getAll()

    @WorkerThread
    suspend fun insertWeightLog(weightLog: WeightLog) {
        weightLogDao.insert(weightLog)
    }

    @WorkerThread
    suspend fun insertFoodLog(foodLog: FoodLog) {
        foodLogDao.insert(foodLog)
    }

    @WorkerThread
    suspend fun insertFoodType(foodType: FoodType) {
        foodTypeDao.insert(foodType)
    }

    //@Suppress("RedundantSuspendModifier")
    //@WorkerThread
    fun getAllWeightLogsForCat(catId: Int): Flow<List<WeightLog>> {
        return weightLogDao.getAllWeightLogsForCat(catId)
    }

    fun getAllFoodLogsForCat(catId: Int): Flow<List<FoodLog>> {
        return foodLogDao.getAllFoodLogsForCat(catId)
    }

    @WorkerThread
    fun getLatestRecordedWeightForCat(catId: Int): Flow<Int>{
        return weightLogDao.getLatestRecordedWeightForCat(catId)
    }

    @WorkerThread
    suspend fun deleteFoodLog(foodLogId: Int) {
        foodLogDao.delete(foodLogId)
    }

    @WorkerThread
    suspend fun deleteWeightLog(weightLogId: Int) {
        weightLogDao.delete(weightLogId)
    }

    val allFoodTypes: Flow<List<FoodType>> = foodTypeDao.getAllFoodTypes()

}