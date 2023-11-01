package com.example.customappproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

class HomeFragment: Fragment(R.layout.fragment_home) {
    private val databaseViewModel: DatabaseViewModel by activityViewModels()
    private var fragmentWeightLogs = listOf<WeightLog>()
    private var fragmentFoodLogs = listOf<FoodLog>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments: Bundle? = arguments
        arguments?.getString("KEY")?.let { Log.i("TESTLOG", it) }

        Log.i("TESTING", "LOADFRAGMENT")

        val catId = 1

        val caloriesChip = view.findViewById<Chip>(R.id.caloriesChip)
        val weightChip = view.findViewById<Chip>(R.id.weightChip)

        val nameText = view.findViewById<TextView>(R.id.nameText)
        val ageText = view.findViewById<TextView>(R.id.ageText)
        val goalWeightText = view.findViewById<TextView>(R.id.goalWeightText)
        val startWeightText = view.findViewById<TextView>(R.id.startWeightText)
        val currentWeightText = view.findViewById<TextView>(R.id.currentWeightText)
        val currentWeightLossRateText = view.findViewById<TextView>(R.id.currentWeightLossRateText)
        val goalWeightLossRateText = view.findViewById<TextView>(R.id.goalWeightLossRateText)

        caloriesChip.isChecked = true

        caloriesChip.setOnClickListener {
            caloriesChip.isChecked = true
            weightChip?.isChecked = false
            drawRecyclerView(fragmentFoodLogs)
            Log.i("TESTING", "caloriesChip")
        }

        weightChip?.setOnClickListener {
            caloriesChip.isChecked = false
            weightChip.isChecked = true
            drawRecyclerView(fragmentWeightLogs)
            Log.i("TESTING", "weightChip")
        }



        databaseViewModel.getAllWeightLogsForCat(catId).observe(viewLifecycleOwner) { weightLogs ->
            Log.i("TESTLOG", weightLogs.toString())
            fragmentWeightLogs = weightLogs;
            Log.i("TESTLOG", fragmentWeightLogs[0].toString())
            if(weightChip.isChecked)
                drawRecyclerView(weightLogs)
        }

        databaseViewModel.getAllFoodLogsForCat(catId).observe(viewLifecycleOwner) { foodLogs ->
            Log.i("TESTLOG", foodLogs.toString())
            fragmentFoodLogs = foodLogs;
            if(caloriesChip.isChecked)
                drawRecyclerView(foodLogs)
        }

        databaseViewModel.allCats.observe(viewLifecycleOwner) {cats ->
            val selectedCat = cats[catId-1]

            nameText.text = "Name: ${selectedCat.name}"
            ageText.text = "Age: PLACEHOLDER"

            goalWeightText.text = "Goal Weight: ${"%.2f".format(selectedCat.goalWeightGrams.toDouble() / 1000)} Kg"
            startWeightText.text = "Start Weight: ${"%.2f".format(selectedCat.startWeightGrams.toDouble() / 1000)} Kg"
            currentWeightLossRateText.text = "Current Loss Rate: PLACEHOLDER"
            goalWeightLossRateText.text = "Goal Loss Rate: ${selectedCat.goalWeightLossRateGramsPerWeek} g/Week"
        }

        databaseViewModel.getLatestRecordedWeightForCat(catId).observe(viewLifecycleOwner) {
            currentWeightText.text = "Current Weight: ${"%.2f".format(it.toDouble() / 1000)} Kg"
        }

        val editCatButton = view.findViewById<Button>(R.id.editCatButton)

        editCatButton?.setOnClickListener {
            val dialogFragment = EditCatDetailsFragment()
            dialogFragment.show(childFragmentManager, "dialog")
            Log.i("TESTING", "caloriesChip")
        }

    }

    fun drawRecyclerView(input: List<*>)
    {
        //calorieLogs.sortByDescending{it.datetime}
        val list = view?.findViewById<RecyclerView>(R.id.genericLogRecyclerView)
        list?.adapter = GenericLogListAdapter(input)
        list?.layoutManager = LinearLayoutManager(requireContext())
    }


}


