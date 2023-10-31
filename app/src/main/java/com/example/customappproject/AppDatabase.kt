package com.example.customappproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Cat::class,WeightLog::class,FoodLog::class,FoodType::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
    abstract fun weightLogDao(): WeightLogDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cat_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.catDao(), database.weightLogDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(catDao: CatDao, weightLogDao: WeightLogDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            catDao.deleteAll()

            var cat = Cat(1, "Jerry", 5000, 4000, 50, "Sedentary", 100)
            catDao.insert(cat)
            cat = Cat(2, "Lucky", 4500, 3900, 45, "Moderately Active", 95)
            catDao.insert(cat)
            //word = Word("World!")
            //catDao.insert(word)

            weightLogDao.deleteAll()
            var weightLog = WeightLog(id=1, catId=1, dateTime =  "32423", catWeightGrams =  5000)
            weightLogDao.insert(weightLog)
            weightLog = WeightLog(catId=1, dateTime =  "324231", catWeightGrams =  5001)
            weightLogDao.insert(weightLog)
        }
}



}
