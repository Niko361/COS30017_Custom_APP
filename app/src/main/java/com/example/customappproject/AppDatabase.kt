package com.example.customappproject

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Database(entities = [Cat::class,WeightLog::class,FoodLog::class,FoodType::class], version = 1)
@TypeConverters(DatabaseTypeConverters::class)
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

            var weightLogs = mutableListOf(
                WeightLog(catId=1, dateTime= LocalDateTime.parse("22/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6040),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("21/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6100),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("20/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6180),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("19/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6200),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("18/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6220),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("17/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6250),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("16/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6300),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("15/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6330),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("14/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6350),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("13/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6380),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("12/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6400),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("11/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6440),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("10/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6450),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("09/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6500),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("23/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6550),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("22/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6600),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("21/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6600),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("20/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6620),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("19/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6620),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("18/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6640),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("17/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6650),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("16/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6660),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("15/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6670),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("14/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6700),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("13/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6710),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("12/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6730),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("11/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6740),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("10/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6750),
                WeightLog(catId=1, dateTime=LocalDateTime.parse("09/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), catWeightGrams=6750),
            )

            weightLogDao.deleteAll()


            weightLogs.forEach {
                weightLogDao.insert(it)
            }




        }
}



}
