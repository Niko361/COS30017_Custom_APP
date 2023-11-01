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
    fun update(cat: Cat)
    @Delete
    fun delete(cat: Cat)
    @Query("DELETE FROM cats")
    suspend fun deleteAll()
}

@Dao
interface WeightLogDao {
    @Query("SELECT * from WeightLogs WHERE cat_id = :catId")
    fun getAllWeightLogsForCat(catId: Int): Flow<List<WeightLog>>
    @Insert
    fun insert(weightLog: WeightLog)
    @Update
    fun update(weightLog: WeightLog)
    @Delete
    fun delete(weightLog: WeightLog)
    @Query("DELETE FROM WeightLogs")
    suspend fun deleteAll()
}

@Dao
interface FoodLogDao {
    @Query("SELECT * from FoodLogs LEFT JOIN FoodTypes ON FoodLogs.food_id = FoodTypes.id WHERE cat_id = :catId")
    fun getAllFoodLogsForCat(catId: Int): Flow<List<FoodLog>>
    @Insert
    fun insert(foodLog: FoodLog)
    @Update
    fun update(foodLog: FoodLog)
    @Delete
    fun delete(foodLog: FoodLog)
    @Query("DELETE FROM FoodLogs")
    suspend fun deleteAll()
}

@Dao
interface FoodTypeDao {
    @Query("SELECT * from FoodTypes")
    fun getAllFoodTypes(): Flow<List<FoodType>>
    @Insert
    fun insert(foodType: FoodType)
    @Update
    fun update(foodType: FoodType)
    @Delete
    fun delete(foodType: FoodType)
    @Query("DELETE FROM FoodTypes")
    suspend fun deleteAll()
}
