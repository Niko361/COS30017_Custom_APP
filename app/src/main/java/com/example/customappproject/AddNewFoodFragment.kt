package com.example.customappproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import java.time.LocalDateTime


class AddNewFoodFragment : DialogFragment() {
    override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
          isCancelable = false
          return inflater.inflate(R.layout.dialog_add_food, container, false)
       }
    private val databaseViewModel: DatabaseViewModel by activityViewModels()


    override fun getTheme(): Int {
          return R.style.DialogTheme
       }
       override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)

       val saveButton = view.findViewById<Button>(R.id.saveButton)
       val closeButton = view.findViewById<Button>(R.id.closeButton)

       val calPer100GInput = view.findViewById<EditText>(R.id.calPer100GInput)
       val brandInput = view.findViewById<EditText>(R.id.brandInput)
       val nameInput = view.findViewById<EditText>(R.id.nameInput)

       var foodCalPer100G: Int = 0
       var foodBrand: String? = null
       var foodName: String? = null

        calPer100GInput.addTextChangedListener {
           foodCalPer100G = if(calPer100GInput.text.isNullOrEmpty())
              0
           else
              calPer100GInput.text.toString().toInt()
           buttonUpdate(foodCalPer100G, foodBrand, foodName)
        }

        brandInput.addTextChangedListener {
         foodBrand = if(brandInput.text.isNullOrEmpty())
             null
         else
             brandInput.text.toString()
         buttonUpdate(foodCalPer100G, foodBrand, foodName)
        }

        nameInput.addTextChangedListener {
         foodName = if(nameInput.text.isNullOrEmpty())
          null
         else
          nameInput.text.toString()
         buttonUpdate(foodCalPer100G, foodBrand, foodName)
        }


       saveButton.setOnClickListener {
           databaseViewModel.insertFoodType(FoodType(foodName="$foodBrand- $foodName", calsPerHundredGrams = foodCalPer100G))
           dismiss()
       }

       closeButton.setOnClickListener {
       dismiss()
       }

        buttonUpdate(foodCalPer100G, foodBrand, foodName)
    }

   private fun buttonUpdate(foodCalPer100G: Int?, foodBrand: String?, foodName: String?){
      val saveButton = view?.findViewById<Button>(R.id.saveButton)
      saveButton?.isEnabled = ((foodCalPer100G != 0) && (foodBrand != null) && (foodName != null))
   }
}



