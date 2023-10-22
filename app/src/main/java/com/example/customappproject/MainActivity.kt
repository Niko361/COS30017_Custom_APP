package com.example.customappproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.navigation.NavigationBarView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    var weightLogs = mutableListOf(
        GenericLogEntry(LocalDateTime.parse("22/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.04Kg"),
        GenericLogEntry(LocalDateTime.parse("21/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.10Kg"),
        GenericLogEntry(LocalDateTime.parse("20/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.18Kg"),
        GenericLogEntry(LocalDateTime.parse("19/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.20Kg"),
        GenericLogEntry(LocalDateTime.parse("18/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.22Kg"),
        GenericLogEntry(LocalDateTime.parse("17/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.25Kg"),
        GenericLogEntry(LocalDateTime.parse("16/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.30Kg"),
        GenericLogEntry(LocalDateTime.parse("15/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.33Kg"),
        GenericLogEntry(LocalDateTime.parse("14/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.35Kg"),
        GenericLogEntry(LocalDateTime.parse("13/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.38Kg"),
        GenericLogEntry(LocalDateTime.parse("12/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.40Kg"),
        GenericLogEntry(LocalDateTime.parse("11/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.44Kg"),
        GenericLogEntry(LocalDateTime.parse("10/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.45Kg"),
        GenericLogEntry(LocalDateTime.parse("09/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.5Kg"),
        GenericLogEntry(LocalDateTime.parse("23/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.55Kg"),
        GenericLogEntry(LocalDateTime.parse("22/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.60Kg"),
        GenericLogEntry(LocalDateTime.parse("21/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.60Kg"),
        GenericLogEntry(LocalDateTime.parse("20/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.62Kg"),
        GenericLogEntry(LocalDateTime.parse("19/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.62Kg"),
        GenericLogEntry(LocalDateTime.parse("18/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.64Kg"),
        GenericLogEntry(LocalDateTime.parse("17/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.65Kg"),
        GenericLogEntry(LocalDateTime.parse("16/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.66Kg"),
        GenericLogEntry(LocalDateTime.parse("15/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.67Kg"),
        GenericLogEntry(LocalDateTime.parse("14/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.70Kg"),
        GenericLogEntry(LocalDateTime.parse("13/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.71Kg"),
        GenericLogEntry(LocalDateTime.parse("12/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.73Kg"),
        GenericLogEntry(LocalDateTime.parse("11/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.74Kg"),
        GenericLogEntry(LocalDateTime.parse("10/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.75Kg"),
        GenericLogEntry(LocalDateTime.parse("09/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.75Kg"),
    )

    var calorieLogs = mutableListOf(
        GenericLogEntry(LocalDateTime.parse("22/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("21/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("20/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("19/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("18/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("17/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("16/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("15/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("14/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("13/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("12/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("11/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("10/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("09/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("23/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("22/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("21/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("20/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("19/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("18/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("17/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("16/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("15/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("14/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("13/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("12/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("11/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("10/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
        GenericLogEntry(LocalDateTime.parse("09/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Cal"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val caloriesChip = findViewById<Chip>(R.id.caloriesChip)
        val weightChip = findViewById<Chip>(R.id.weightChip)

        caloriesChip.isChecked = true

        caloriesChip.setOnClickListener {
            caloriesChip.isChecked = true
            weightChip.isChecked = false
            drawRecyclerView(calorieLogs)
        }

        weightChip.setOnClickListener {
            caloriesChip.isChecked = false
            weightChip.isChecked = true
            drawRecyclerView(weightLogs)
        }

        val editCatButton = findViewById<Button>(R.id.editCatButton)
        val closeEditCatButton = findViewById<Button>(R.id.closeEditCatButton)

        editCatButton.setOnClickListener {
            val dialogFragment = EditCatDetailsFragment()
            dialogFragment.show(supportFragmentManager, "dialog")

            /*
            // The device is smaller, so show the fragment fullscreen.
            val transaction = fragmentManager.beginTransaction()
            // For a polished look, specify a transition animation.
            transaction.setTransition(dialogFragment.TRANSIT_FRAGMENT_OPEN)
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity.
            transaction
                .add(android.R.id.content, dialogFragment)
                .addToBackStack(null)
                .commit() */


        }




        weightLogs.sortByDescending{it.datetime}

        drawRecyclerView(calorieLogs)

        val bottomNavBar = findViewById<NavigationBarView>(R.id.bottom_navigation)
        bottomNavBar.selectedItemId = R.id.home
        bottomNavBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.weight_cat -> {
                    Log.i("LOGME", "WEIGH")
                    val intent = Intent(this, LogWeightActivity::class.java)
                    startActivity(intent)
                    true

                }
                R.id.feed_cat -> {
                    Log.i("LOGME", "FEED")
                    val intent = Intent(this, LogFoodEntryActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.home -> {
                    Log.i("LOGME", "HOME")
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val bottomNavBar = findViewById<NavigationBarView>(R.id.bottom_navigation)
        bottomNavBar.selectedItemId = R.id.home
    }

    fun drawRecyclerView(input: MutableList<GenericLogEntry>)
    {

        input.sortByDescending{it.datetime}
        val list = findViewById<RecyclerView>(R.id.genericLogRecyclerView)

        list.adapter = GenericLogListAdapter(input)
        list.layoutManager = LinearLayoutManager(this)
    }
}