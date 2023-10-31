package com.example.customappproject

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class CatRepository(private val catDao: CatDao) {

    val allCats: Flow<List<Cat>> = catDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cat: Cat) {
        catDao.insert(cat)
    }

}