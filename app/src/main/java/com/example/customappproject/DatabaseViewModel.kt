package com.example.customappproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: DatabaseRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allCats: LiveData<List<Cat>> = repository.allCats.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertCat(cat: Cat) = viewModelScope.launch {
        repository.insertCat(cat)
    }

    fun updateCat(cat: Cat) = viewModelScope.launch {
        repository.updateCat(cat)
    }


    fun getAllWeightLogsForCat(catId: Int): LiveData<List<WeightLog>>{
        return repository.getAllWeightLogsForCat(catId).asLiveData()
    }

    fun getAllFoodLogsForCat(catId: Int): LiveData<List<FoodLog>>{
        return repository.getAllFoodLogsForCat(catId).asLiveData()
    }

    val allFoodTypes: LiveData<List<FoodType>> = repository.allFoodTypes.asLiveData()

    fun insertWeightEntry(weightLog: WeightLog) = viewModelScope.launch {
        repository.insertWeightLog(weightLog)
    }

    fun insertFoodEntry(foodLog: FoodLog) = viewModelScope.launch {
        repository.insertFoodLog(foodLog)
    }

    fun insertFoodType(foodType: FoodType) = viewModelScope.launch{
        repository.insertFoodType(foodType)
    }

    suspend fun getLatestRecordedWeightForCat(catId: Int): Int {
        return repository.getLatestRecordedWeightForCat(catId)
    }


}

class WordViewModelFactory(private val repository: DatabaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DatabaseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
