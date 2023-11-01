package com.example.customappproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.MaterialAutoCompleteTextView


class EditCatDetailsFragment : DialogFragment() {
 override fun onCreateView(
  inflater: LayoutInflater,
  container: ViewGroup?,
  savedInstanceState: Bundle?
 ): View? {
  isCancelable = false
  return inflater.inflate(R.layout.dialog_edit_cat, container, false)
 }

 private val databaseViewModel: DatabaseViewModel by activityViewModels()


 override fun getTheme(): Int {
  return R.style.DialogTheme
 }

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)

  val catId = 1
  var selectedCat: Cat? = null

  val saveButton = view.findViewById<Button>(R.id.saveButton)
  val closeEditCatButton = view.findViewById<Button>(R.id.closeEditCatButton)

  val nameInput = view.findViewById<EditText>(R.id.nameInput)
  val startWeightInput = view.findViewById<EditText>(R.id.startWeightInput)
  val goalWeightInput = view.findViewById<EditText>(R.id.goalWeightInput)
  val goalDailyCaloriesInput = view.findViewById<EditText>(R.id.goalDailyCaloriesInput)
  val selectActivityLevelMenu =
   view.findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.selectActivityLevelMenu)
  val selectActivityLevelItem = view.findViewById<AutoCompleteTextView>(R.id.selectActivityLevelItem)

  val items =
   arrayOf("Sedentary", "Lightly Active", "Moderately Active", "Very Active", "Extremely Active")
  (selectActivityLevelMenu.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)


  var nameValue: String = ""
  var startWeightValue: Int = 0
  var goalWeightValue: Int = 0
  var goalDailyCaloriesValue: Int = 0
  var activityLevelValue: String = ""

  databaseViewModel.allCats.observe(viewLifecycleOwner) { cats ->
   selectedCat = cats[catId - 1]

   nameValue = selectedCat!!.name
   startWeightValue = selectedCat!!.startWeightGrams
   goalWeightValue = selectedCat!!.goalWeightGrams
   goalDailyCaloriesValue = selectedCat!!.goalDailyCalories
   activityLevelValue = selectedCat!!.activityLevel

   //initialise field values
   nameInput.setText(nameValue)
   startWeightInput.setText((startWeightValue/1000).toString())
   goalWeightInput.setText((goalWeightValue/1000).toString())
   goalDailyCaloriesInput.setText(goalDailyCaloriesValue.toString())
   selectActivityLevelMenu.hint = activityLevelValue
  }

  nameInput.addTextChangedListener {
   nameValue = if(nameInput.text.isNullOrEmpty())
    ""
   else
    nameInput.text.toString()
   updateSaveButton(nameValue, startWeightValue, goalWeightValue, goalDailyCaloriesValue, activityLevelValue)
  }

  startWeightInput.addTextChangedListener {
   startWeightValue = if(startWeightInput.text.isNullOrEmpty())
    0
   else
    startWeightInput.text.toString().toInt()*1000
   updateSaveButton(nameValue, startWeightValue, goalWeightValue, goalDailyCaloriesValue, activityLevelValue)
  }

  goalWeightInput.addTextChangedListener {
   goalWeightValue = if(goalWeightInput.text.isNullOrEmpty())
    0
   else
    goalWeightInput.text.toString().toInt()*1000
   updateSaveButton(nameValue, startWeightValue, goalWeightValue, goalDailyCaloriesValue, activityLevelValue)
  }

  goalDailyCaloriesInput.addTextChangedListener {
   goalDailyCaloriesValue = if(goalDailyCaloriesInput.text.isNullOrEmpty())
    0
   else
    goalDailyCaloriesInput.text.toString().toInt()
   updateSaveButton(nameValue, startWeightValue, goalWeightValue, goalDailyCaloriesValue, activityLevelValue)
  }

  selectActivityLevelItem.addTextChangedListener {
     activityLevelValue = selectActivityLevelItem.text.toString()
  }


  saveButton.setOnClickListener {
   databaseViewModel.updateCat(Cat(catId, nameValue, startWeightValue, goalWeightValue, 50, activityLevelValue, goalDailyCaloriesValue))
   dismiss()
  }

  closeEditCatButton.setOnClickListener {
   dismiss()
  }

 }

 fun updateSaveButton(
  nameValue: String,
  currentWeightValue: Int,
  goalWeightValue: Int,
  goalDailyCaloriesValue: Int,
  activityLevelValue: String
 ) {

   val saveButton = view?.findViewById<Button>(R.id.saveButton)
   saveButton?.isEnabled = ((nameValue != "") && (currentWeightValue != 0) && (goalWeightValue != 0) && (goalDailyCaloriesValue != 0) && (activityLevelValue != ""))
 }
}









