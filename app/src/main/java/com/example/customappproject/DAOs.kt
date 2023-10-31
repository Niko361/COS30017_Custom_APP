package com.example.customappproject

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cats")
    fun getAll(): Flow<List<Cat>>

    @Query("SELECT * FROM cats WHERE id = (:id)")
    fun getCatById(id: Int): List<Cat>

    @Insert
    fun insert(cat: Cat)

    @Delete
    fun delete(cat: Cat)

    @Query("DELETE FROM cats")
    suspend fun deleteAll()
}
