package com.example.customappproject

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class DatabaseRepository(private val catDao: CatDao, private val weightLogDao: WeightLogDao) {

    val allCats: Flow<List<Cat>> = catDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCat(cat: Cat) {
        catDao.insert(cat)
    }

    //val weightLogsForCat: Flow<List<Cat>> = catDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertWeightLog(weightLog: WeightLog) {
        weightLogDao.insert(weightLog)
    }

    //@Suppress("RedundantSuspendModifier")
    //@WorkerThread
    fun getWeightLogsForCat(catId: Int): Flow<List<WeightLog>> {
        return weightLogDao.getAllWeightLogsForCat(catId)
    }

}