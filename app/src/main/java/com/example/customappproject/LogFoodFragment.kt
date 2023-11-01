package com.example.customappproject

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.time.LocalDateTime

class LogFoodFragment: Fragment(R.layout.fragment_log_food){
    private val databaseViewModel: DatabaseViewModel by activityViewModels()
    private var allFoodTypes = listOf<FoodType>()
    private var dailyCalorieGoal = 100
    private var foodEntryWeight = 0
    private var foodEntryCalories = 0
    private var selectedFoodType: FoodType? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logFoodButton = view.findViewById<Button>(R.id.logFoodButton)
        val selectFoodItem = view.findViewById<AutoCompleteTextView>(R.id.selectFoodItem)
        val foodWeightEditText = view.findViewById<EditText>(R.id.enterFoodWeight)


        //var foodEntryCalories = 0


        databaseViewModel.getAllFoodLogsForCat(1).observe(viewLifecycleOwner) { foodLogs ->
            drawRecyclerView(foodLogs)
        }

        databaseViewModel.allFoodTypes.observe(viewLifecycleOwner) { foodTypes ->
            allFoodTypes = foodTypes
            val selectFoodMenu = view.findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.selectFoodMenu)
            var foodNames = mutableListOf<String>()
            foodTypes.forEach {
                foodNames.add("${it.foodName} (${it.calsPerHundredGrams} Cal/100g)")
            }
            foodNames.add("Add New Food")

            (selectFoodMenu.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(foodNames.toTypedArray())
        }

        selectFoodItem.addTextChangedListener {
            allFoodTypes.forEach {
                if(selectFoodItem.text.toString() == "${it.foodName} (${it.calsPerHundredGrams} Cal/100g)")
                    selectedFoodType = it
            }

            if(selectFoodItem.text.toString() == "Add New Food"){
                selectedFoodType = null
                val dialogFragment = AddNewFoodFragment()
                dialogFragment.show(childFragmentManager, "dialog")
            }

            foodUpdate()

            //logFoodButton.isEnabled = ((foodEntryWeight != 0) && (selectedFoodType != null))

            Log.i("TESTLOG", selectedFoodType.toString())
        }


        logFoodButton.setOnClickListener {
            //calorieLogs.add(GenericLogEntry(LocalDateTime.now(), "${foodEntryWeight} Calories"))
            //drawRecyclerView()
            // close keyboard after entering weight
            //val view: View? = this.currentFocus
            databaseViewModel.insertFoodEntry(FoodLog(catId = 1, dateTime = LocalDateTime.now(), foodWeightGrams = foodEntryWeight, calories = foodEntryCalories, foodId = selectedFoodType?.id))


            if (view != null) {
                val inputMethodManager = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
                Log.i("TESTING", "close keyboard?")
            }
        }

        foodWeightEditText.addTextChangedListener {
            foodEntryWeight = if(foodWeightEditText.text.isNullOrEmpty())
                0
            else
                foodWeightEditText.text.toString().toInt()
            foodUpdate()
        }
        foodUpdate()
    }

    //function that is called when either the selected food type or food weight is updated
    fun foodUpdate(){
        val logFoodButton = view?.findViewById<Button>(R.id.logFoodButton)
        val remainingDailyCalories = view?.findViewById<TextView>(R.id.remainingDailyCalories)

        if(foodEntryWeight != 0 && selectedFoodType != null)
            foodEntryCalories = (foodEntryWeight*selectedFoodType!!.calsPerHundredGrams)/100

        //don't allow the user to log zero grams of food
        logFoodButton?.isEnabled = ((foodEntryWeight != 0) && (selectedFoodType != null))
        remainingDailyCalories?.text = "${dailyCalorieGoal-foodEntryCalories}/${dailyCalorieGoal}"
    }


    fun drawRecyclerView(input: List<FoodLog>)
    {
        //calorieLogs.sortByDescending{it.datetime}
        val list = view?.findViewById<RecyclerView>(R.id.genericLogRecyclerView)
        list?.adapter = GenericLogListAdapter(input)
        list?.layoutManager = LinearLayoutManager(requireContext())
    }

}