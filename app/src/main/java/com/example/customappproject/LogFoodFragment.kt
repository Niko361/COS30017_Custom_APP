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

class LogFoodFragment: Fragment(R.layout.fragment_log_food){
    private val databaseViewModel: DatabaseViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseViewModel.getAllFoodLogsForCat(1).observe(viewLifecycleOwner) { foodLogs ->
            Log.i("TESTLOG", foodLogs.toString())
            drawRecyclerView(foodLogs)
        }

        databaseViewModel.allFoodTypes.observe(viewLifecycleOwner) { foodTypes ->
            val selectFoodMenu = view.findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.selectFoodMenu)
            var foodNames = mutableListOf<String>()
            foodTypes.forEach {
                foodNames.add(it.foodName)
            }
            foodNames.add("Add New Food")

            //val items = arrayOf("Whiskas, Wet", "Friskies, Dry", "Purina, Wet", "Add New Food")
            (selectFoodMenu.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(foodNames.toTypedArray())
        }

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        var foodEntryWeight: Int = 0
        var dailyCalorieGoal: Int = 100




        val selectFoodItem = view.findViewById<AutoCompleteTextView>(R.id.selectFoodItem)
        selectFoodItem.addTextChangedListener {
            Log.i("CLICKY", selectFoodItem.text.toString())

            if(selectFoodItem.text.toString() == "Add New Food"){
                val dialogFragment = AddNewFoodFragment()
                dialogFragment.show(childFragmentManager, "dialog")
            }
        }

        val logFoodButton = view.findViewById<Button>(R.id.logFoodButton)

        logFoodButton.setOnClickListener {
            //calorieLogs.add(GenericLogEntry(LocalDateTime.now(), "${foodEntryWeight} Calories"))
            //drawRecyclerView()
            // close keyboard after entering weight
            //val view: View? = this.currentFocus
            if (view != null) {
                val inputMethodManager = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
                Log.i("TESTING", "close keyboard?")
            }
        }

        val saveButton = view.findViewById<Button>(R.id.logFoodButton)

        val remainingDailyCalories = view.findViewById<TextView>(R.id.remainingDailyCalories)

        remainingDailyCalories.text = "${dailyCalorieGoal-foodEntryWeight}/${dailyCalorieGoal}"


        //drawRecyclerView()

        val foodWeightEditText = view.findViewById<EditText>(R.id.enterFoodWeight)
        foodWeightEditText.addTextChangedListener {
            foodEntryWeight = if(foodWeightEditText.text.isNullOrEmpty())
                0
            else
                foodWeightEditText.text.toString().toInt()

            //don't allow the user to log zero grams of food
            saveButton.isEnabled = (foodEntryWeight != 0)
            remainingDailyCalories.text = "${dailyCalorieGoal-foodEntryWeight}/${dailyCalorieGoal}"
        }
    }




    fun drawRecyclerView(input: List<FoodLog>)
    {
        //calorieLogs.sortByDescending{it.datetime}
        val list = view?.findViewById<RecyclerView>(R.id.genericLogRecyclerView)
        list?.adapter = GenericLogListAdapter(input)
        list?.layoutManager = LinearLayoutManager(requireContext())
    }

}