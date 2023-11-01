package com.example.customappproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.navigation.NavigationBarView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment: Fragment(R.layout.fragment_home) {
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

    var calorieLogs = mutableListOf(
        FoodLog(catId=1, dateTime=LocalDateTime.parse("22/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("21/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("20/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("19/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("18/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("17/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("16/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("15/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("14/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("13/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("12/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("11/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("10/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("09/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("23/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("22/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("21/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("20/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("19/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("18/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("17/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("16/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("15/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("14/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("13/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("12/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("11/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("10/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
        FoodLog(catId=1, dateTime=LocalDateTime.parse("09/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), foodWeightGrams=40, calories=80),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("TESTING", "LOADFRAGMENT")

        val caloriesChip = view.findViewById<Chip>(R.id.caloriesChip)
        val weightChip = view.findViewById<Chip>(R.id.weightChip)

        caloriesChip.isChecked = true

        caloriesChip.setOnClickListener {
            caloriesChip.isChecked = true
            weightChip?.isChecked = false
            drawRecyclerView(calorieLogs)
            Log.i("TESTING", "caloriesChip")
        }

        weightChip?.setOnClickListener {
            caloriesChip.isChecked = false
            weightChip.isChecked = true
            drawRecyclerView(weightLogs)
            Log.i("TESTING", "weightChip")
        }

        val editCatButton = view.findViewById<Button>(R.id.editCatButton)

        editCatButton?.setOnClickListener {
            val dialogFragment = EditCatDetailsFragment()
            dialogFragment.show(childFragmentManager, "dialog")
            Log.i("TESTING", "caloriesChip")
        }

        //weightLogs.sortByDescending{it.datetime}

        drawRecyclerView(calorieLogs)

    }

    fun drawRecyclerView(input: MutableList<*>)
    {

        //input.sortByDescending{it.datetime}
        val list = view?.findViewById<RecyclerView>(R.id.genericLogRecyclerView)

        list?.adapter = GenericLogListAdapter(input)
        list?.layoutManager = LinearLayoutManager(requireContext())
    }
}


