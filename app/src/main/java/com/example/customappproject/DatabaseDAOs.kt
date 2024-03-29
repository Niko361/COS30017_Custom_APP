package com.example.customappproject

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {
    @Query("SELECT * FROM cats")
    fun getAll(): Flow<List<Cat>>
    @Query("SELECT * FROM cats WHERE id = (:id)")
    fun getCatById(id: Int): List<Cat>
    @Insert
    fun insert(cat: Cat)
    @Update
    suspend fun update(cat: Cat)
    @Delete
    fun delete(cat: Cat)
    @Query("DELETE FROM cats")
    suspend fun deleteAll()
}

@Dao
interface WeightLogDao {
    @Query("SELECT * from WeightLogs WHERE cat_id = :catId ORDER BY id DESC")
    fun getAllWeightLogsForCat(catId: Int): Flow<List<WeightLog>>
    @Insert
    suspend fun insert(weightLog: WeightLog)
    @Update
    fun update(weightLog: WeightLog)
    @Query("DELETE FROM WeightLogs WHERE id = :weightLogId")
    suspend fun delete(weightLogId: Int)
    @Query("DELETE FROM WeightLogs")
    suspend fun deleteAll()
    @Query("SELECT cat_weight_grams from WeightLogs WHERE cat_id = :catId ORDER BY id DESC LIMIT 1")
    fun getLatestRecordedWeightForCat(catId: Int): Flow<Int>
}

@Dao
interface FoodLogDao {
    @Query("SELECT FoodLogs.id, FoodLogs.cat_id, FoodLogs.datetime, FoodLogs.food_weight_grams, FoodLogs.food_id, FoodLogs.calories, FoodTypes.food_name, FoodTypes.cals_per_hundred_grams from FoodLogs LEFT JOIN FoodTypes ON FoodLogs.food_id = FoodTypes.id WHERE cat_id = :catId ORDER BY FoodLogs.id DESC")
    fun getAllFoodLogsForCat(catId: Int): Flow<List<FoodLog>>
    @Insert
    suspend fun insert(foodLog: FoodLog)
    @Update
    fun update(foodLog: FoodLog)
    @Query("DELETE FROM FoodLogs WHERE id = :foodLogId")
    suspend fun delete(foodLogId: Int)
    @Query("DELETE FROM FoodLogs")
    suspend fun deleteAll()
}

@Dao
interface FoodTypeDao {
    @Query("SELECT * from FoodTypes")
    fun getAllFoodTypes(): Flow<List<FoodType>>
    @Insert
    suspend fun insert(foodType: FoodType)
    @Update
    fun update(foodType: FoodType)
    @Delete
    fun delete(foodType: FoodType)
    @Query("DELETE FROM FoodTypes")
    suspend fun deleteAll()
}
