package com.example.customappproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationBarView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogFoodEntry: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_food)



        val bottomNavBar = findViewById<NavigationBarView>(R.id.bottom_navigation)


        bottomNavBar.setOnItemSelectedListener { item ->
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
                    super.onBackPressed()
                    true
                }
                else -> false
            }
        }

    }

}