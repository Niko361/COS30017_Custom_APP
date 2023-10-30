package com.example.customappproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.navigation.NavigationBarView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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