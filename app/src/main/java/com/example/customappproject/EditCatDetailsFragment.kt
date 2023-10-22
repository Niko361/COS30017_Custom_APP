package com.example.customappproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
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

 override fun getTheme(): Int {
  return R.style.DialogTheme
 }

 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)

  val saveButton = view.findViewById<Button>(R.id.saveButton)
  saveButton.setOnClickListener {
   dismiss()
  }

  val closeEditCatButton = view.findViewById<Button>(R.id.closeEditCatButton)
  closeEditCatButton.setOnClickListener {
   dismiss()
  }

  val selectActivityLevelMenu = view.findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.selectActivityLevelMenu)
  val items = arrayOf("Sedentary", "Lightly Active", "Moderately Active", "Very Active", "Extremely Active")
  (selectActivityLevelMenu.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)

 }
}



