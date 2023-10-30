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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogFoodEntryFragment: Fragment(R.layout.fragment_log_food){
    var calorieLogs = mutableListOf(
        GenericLogEntry(LocalDateTime.parse("22/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("21/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("20/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("19/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("18/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("17/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("16/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("15/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("14/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("13/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("12/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("11/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("10/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("09/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("23/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("22/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("21/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("20/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("19/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("18/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("17/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("16/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("15/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("14/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("13/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("12/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("11/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("10/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
        GenericLogEntry(LocalDateTime.parse("09/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "80 Calories"),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        var foodEntryWeight: Int = 0
        var dailyCalorieGoal: Int = 100


        val selectFoodMenu = view.findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.selectFoodMenu)
        val items = arrayOf("Whiskas, Wet", "Friskies, Dry", "Purina, Wet", "Add New Food")
        (selectFoodMenu.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)

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
            calorieLogs.add(GenericLogEntry(LocalDateTime.now(), "${foodEntryWeight} Calories"))
            drawRecyclerView()
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


        drawRecyclerView()

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




    fun drawRecyclerView()
    {
        calorieLogs.sortByDescending{it.datetime}
        val list = view?.findViewById<RecyclerView>(R.id.genericLogRecyclerView)
        list?.adapter = GenericLogListAdapter(calorieLogs)
        list?.layoutManager = LinearLayoutManager(requireContext())
    }

}