package com.example.customappproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity (tableName = "Cats")
data class Cat(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "start_weight_grams") val startWeightGrams: Int,
    @ColumnInfo(name = "goal_weight_grams") val goalWeightGrams: Int,
    @ColumnInfo(name = "goal_weight_loss_rate_grams_per_week") val goalWeightLossRateGramsPerWeek: Int,
    @ColumnInfo(name = "activity_level") val activityLevel: String,
    @ColumnInfo(name = "goal_daily_calories") val goalDailyCalories: Int
)

@Entity (tableName = "WeightLogs", foreignKeys = [ForeignKey(entity = Cat::class, parentColumns = arrayOf("id"), childColumns = arrayOf("cat_id"), onDelete = ForeignKey.CASCADE)] )
data class WeightLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "cat_id") val catId: Int,
    @ColumnInfo(name = "datetime") val dateTime: LocalDateTime,
    @ColumnInfo(name = "cat_weight_grams") val catWeightGrams: Int
)

@Entity (tableName = "FoodTypes")
data class FoodType(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "food_name") val foodName: String,
    @ColumnInfo(name = "cals_per_hundred_grams") val calsPerHundredGrams: Int
)
@Entity (tableName = "FoodLogs", foreignKeys = [ForeignKey(entity = Cat::class, parentColumns = arrayOf("id"), childColumns = arrayOf("cat_id"), onDelete = ForeignKey.CASCADE),
                                                ForeignKey(entity = FoodType::class, parentColumns = arrayOf("id"), childColumns = arrayOf("food_id"), onDelete = ForeignKey.SET_NULL)] )
data class FoodLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "cat_id") val catId: Int,
    @ColumnInfo(name = "datetime") val dateTime: LocalDateTime,
    @ColumnInfo(name = "food_weight_grams") val foodWeightGrams: Int,
    @ColumnInfo(name = "food_id") val foodId: Int? = null,
    @ColumnInfo(name = "calories") val calories: Int
)




