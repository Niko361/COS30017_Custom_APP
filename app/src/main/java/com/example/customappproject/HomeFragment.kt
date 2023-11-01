package com.example.customappproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment: Fragment(R.layout.fragment_home) {
    private val databaseViewModel: DatabaseViewModel by activityViewModels()
    private var fragmentWeightLogs = listOf<WeightLog>()
    private var fragmentFoodLogs = listOf<FoodLog>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments: Bundle? = arguments
        arguments?.getString("KEY")?.let { Log.i("TESTLOG", it) }

        Log.i("TESTING", "LOADFRAGMENT")

        val caloriesChip = view.findViewById<Chip>(R.id.caloriesChip)
        val weightChip = view.findViewById<Chip>(R.id.weightChip)

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

        databaseViewModel.getAllWeightLogsForCat(1).observe(viewLifecycleOwner) { weightLogs ->
            Log.i("TESTLOG", weightLogs.toString())
            fragmentWeightLogs = weightLogs;
            if(weightChip.isChecked)
                drawRecyclerView(weightLogs)
        }

        databaseViewModel.getAllFoodLogsForCat(1).observe(viewLifecycleOwner) { foodLogs ->
            Log.i("TESTLOG", foodLogs.toString())
            fragmentFoodLogs = foodLogs;
            if(caloriesChip.isChecked)
                drawRecyclerView(foodLogs)
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


