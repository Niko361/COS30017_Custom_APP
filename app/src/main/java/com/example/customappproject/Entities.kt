package com.example.customappproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "cats")
data class Cat(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "current_weight_grams") val currentWeightGrams: Int,
    @ColumnInfo(name = "goal_weight_grams") val goalWeightGrams: Int,
    @ColumnInfo(name = "goal_weight_loss_rate_grams_per_week") val goalWeightLossRateGramsPerWeek: Int,
    @ColumnInfo(name = "activity_level") val activityLevel: String,
    @ColumnInfo(name = "goal_daily_calories") val goalDailyCalories: Int
)
