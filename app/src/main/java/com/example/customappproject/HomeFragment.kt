package com.example.customappproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip


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

            nameText.text = "${resources.getString(R.string.name_label)} ${selectedCat.name}"
            ageText.text = "${resources.getString(R.string.age_label)} ${resources.getString(R.string.placeholder)}"

            goalWeightText.text = "${resources.getString(R.string.goal_weight_label)} ${"%.2f".format(selectedCat.goalWeightGrams.toDouble() / 1000)} ${resources.getString(R.string.kg)}"
            startWeightText.text = "${resources.getString(R.string.start_weight_label)} ${"%.2f".format(selectedCat.startWeightGrams.toDouble() / 1000)} ${resources.getString(R.string.kg)}"
            currentWeightLossRateText.text = "${resources.getString(R.string.current_loss_rate_label)} ${resources.getString(R.string.placeholder)}"
            goalWeightLossRateText.text = "${resources.getString(R.string.goal_loss_rate_label)} ${selectedCat.goalWeightLossRateGramsPerWeek} ${resources.getString(R.string.g_week)}"
        }

        databaseViewModel.getLatestRecordedWeightForCat(catId).observe(viewLifecycleOwner) {
            currentWeightText.text = "${resources.getString(R.string.current_weight_label)} ${"%.2f".format(it.toDouble() / 1000)} ${resources.getString(R.string.kg)}"
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


