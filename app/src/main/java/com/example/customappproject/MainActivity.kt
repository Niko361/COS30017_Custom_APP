package com.example.customappproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationBarView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MainActivity : AppCompatActivity() {
    private val databaseViewModel: DatabaseViewModel by viewModels {
        WordViewModelFactory((application as CatApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val allCats = databaseViewModel.allCats

        databaseViewModel.allCats.observe(this) {cats ->
            //Log.i("TESTLOG", cats.toString())
            //cats?.let {
            //    Log.i("TESTLOG", it.toString())
            //}
        }

        databaseViewModel.getAllWeightLogsForCat(1).observe(this) { weightLogs ->
            //Log.i("TESTLOG", weightLogs.toString())
        }

        databaseViewModel.getAllFoodLogsForCat(1).observe(this) { foodLogs ->
            //Log.i("TESTLOG", foodLogs.toString())
        }

        databaseViewModel.allFoodTypes.observe(this) {foodTypes ->
            //Log.i("TESTLOG", foodTypes.toString())
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
                    replaceFragment(LogFoodFragment())
                }
                R.id.home -> {
                    //Log.i("LOGME", "HOME")
                    replaceFragment(HomeFragment())
                }
                else -> Log.i("TESTLOG", "Invalid Navbar state")

            }
            true
        }

        bottomNavBar.setOnItemReselectedListener { item ->
            when(item.itemId) {
                R.id.weight_cat -> {
                    Log.i("LOGME", "WEIGH")
                    true

                }
                R.id.feed_cat -> {
                    Log.i("LOGME", "FEED")
                    true
                }
                R.id.home -> {
                    Log.i("LOGME", "HOME")
                    val modalBottomSheet = SelectCatBottomSheetDialogFragment()
                    modalBottomSheet.show(supportFragmentManager, SelectCatBottomSheetDialogFragment.TAG)

                    true
                }
                else -> false
            }
        }


    }

    private fun replaceFragment(fragment : Fragment){
        val arguments: Bundle = Bundle()
        arguments.putString("KEY", "STRING")
        fragment.arguments = arguments

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