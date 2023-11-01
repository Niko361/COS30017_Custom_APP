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

    //val weightLogsForCat: Flow<List<Cat>> = catDao.getAll()

    @WorkerThread
    suspend fun insertWeightLog(weightLog: WeightLog) {
        weightLogDao.insert(weightLog)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertFoodLog(foodLog: FoodLog) {
        foodLogDao.insert(foodLog)
    }

    //@Suppress("RedundantSuspendModifier")
    //@WorkerThread
    fun getAllWeightLogsForCat(catId: Int): Flow<List<WeightLog>> {
        return weightLogDao.getAllWeightLogsForCat(catId)
    }

    fun getAllFoodLogsForCat(catId: Int): Flow<List<FoodLog>> {
        return foodLogDao.getAllFoodLogsForCat(catId)
    }

    val allFoodTypes: Flow<List<FoodType>> = foodTypeDao.getAllFoodTypes()

}