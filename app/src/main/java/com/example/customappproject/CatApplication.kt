package com.example.customappproject

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CatApplication : Application() {
        // No need to cancel this scope as it'll be torn down with the process
        val applicationScope = CoroutineScope(SupervisorJob())


        // Using by lazy so the database and the repository are only created when they're needed
        // rather than when the application starts
        val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
        val repository by lazy { DatabaseRepository(database.catDao(), database.weightLogDao()) }
    }




