package com.example.customappproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationBarView
import androidx.fragment.app.Fragment
import androidx.room.ColumnInfo
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    private val databaseViewModel: DatabaseViewModel by viewModels {
        WordViewModelFactory((application as CatApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val allCats = databaseViewModel.allCats

        databaseViewModel.allCats.observe(this) {cats ->
            Log.i("TESTLOG", cats.toString())
            //cats?.let {
            //    Log.i("TESTLOG", it.toString())
            //}
        }

        //Log.i("TESTLOG", databaseViewModel.allCats.toString())




        //db.catDao().insert(Cat(1, "Jerry", 5000, 4000, 50, "Sedentary", 100))

        //Log.i("TESTLOG", Cat(1, "Jerry", 5000, 4000, 50, "Sedentary", 100).toString())

        //Log.i("TESTLOG", db.catDao().getAll().toString())


        replaceFragment(HomeFragment())
        val bottomNavBar = findViewById<NavigationBarView>(R.id.bottom_navigation)
        bottomNavBar.selectedItemId = R.id.home
        bottomNavBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.weight_cat -> {
                    //Log.i("LOGME", "WEIGH")
                    replaceFragment(LogWeightFragment())

                }
                R.id.feed_cat -> {
                    //Log.i("LOGME", "FEED")
                    replaceFragment(LogFoodEntryFragment())
                }
                R.id.home -> {
                    //Log.i("LOGME", "HOME")
                    replaceFragment(HomeFragment())
                }
                else -> Log.i("TESTLOG", "Invalid Navbar state")

            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()


    }

    override fun onResume() {
        super.onResume()
        //val bottomNavBar = findViewById<NavigationBarView>(R.id.bottom_navigation)
        //bottomNavBar.selectedItemId = R.id.home
    }
}