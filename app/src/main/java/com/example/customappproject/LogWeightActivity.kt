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

class LogWeightActivity: AppCompatActivity() {
    var weightLogs = mutableListOf(
        GenericLogEntry(LocalDateTime.parse("23/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.0kg"),
        GenericLogEntry(LocalDateTime.parse("22/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.04kg"),
        GenericLogEntry(LocalDateTime.parse("21/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.10kg"),
        GenericLogEntry(LocalDateTime.parse("20/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.18kg"),
        GenericLogEntry(LocalDateTime.parse("19/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.20kg"),
        GenericLogEntry(LocalDateTime.parse("18/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.22kg"),
        GenericLogEntry(LocalDateTime.parse("17/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.25kg"),
        GenericLogEntry(LocalDateTime.parse("16/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.30kg"),
        GenericLogEntry(LocalDateTime.parse("15/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.33kg"),
        GenericLogEntry(LocalDateTime.parse("14/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.35kg"),
        GenericLogEntry(LocalDateTime.parse("13/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.38kg"),
        GenericLogEntry(LocalDateTime.parse("12/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.40kg"),
        GenericLogEntry(LocalDateTime.parse("11/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.44kg"),
        GenericLogEntry(LocalDateTime.parse("10/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.45kg"),
        GenericLogEntry(LocalDateTime.parse("09/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.5kg"),
        GenericLogEntry(LocalDateTime.parse("23/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.55kg"),
        GenericLogEntry(LocalDateTime.parse("22/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.60kg"),
        GenericLogEntry(LocalDateTime.parse("21/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.60kg"),
        GenericLogEntry(LocalDateTime.parse("20/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.62kg"),
        GenericLogEntry(LocalDateTime.parse("19/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.62kg"),
        GenericLogEntry(LocalDateTime.parse("18/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.64kg"),
        GenericLogEntry(LocalDateTime.parse("17/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.65kg"),
        GenericLogEntry(LocalDateTime.parse("16/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.66kg"),
        GenericLogEntry(LocalDateTime.parse("15/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.67kg"),
        GenericLogEntry(LocalDateTime.parse("14/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.70kg"),
        GenericLogEntry(LocalDateTime.parse("13/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.71kg"),
        GenericLogEntry(LocalDateTime.parse("12/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.73kg"),
        GenericLogEntry(LocalDateTime.parse("11/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.74kg"),
        GenericLogEntry(LocalDateTime.parse("10/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.75kg"),
        GenericLogEntry(LocalDateTime.parse("09/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.75kg"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_weight)


        drawRecyclerView()

        val bottomNavBar = findViewById<NavigationBarView>(R.id.bottom_navigation)

        bottomNavBar.selectedItemId = R.id.weight_cat


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

    fun drawRecyclerView()
    {
        weightLogs.sortByDescending{it.datetime}
        val list = findViewById<RecyclerView>(R.id.genericLogRecyclerView)
        list.adapter = GenericLogListAdapter(weightLogs)
        list.layoutManager = LinearLayoutManager(this)
    }
}