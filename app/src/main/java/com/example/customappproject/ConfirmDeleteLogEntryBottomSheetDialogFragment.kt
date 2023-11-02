package com.example.customappproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfirmDeleteLogEntryBottomSheetDialogFragment() : BottomSheetDialogFragment() { //item: Any?
    private val databaseViewModel: DatabaseViewModel by activityViewModels()
    private var foodLogId: Int? = null
    private var weightLogId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            foodLogId = it.getInt("foodLogId")
            weightLogId = it.getInt("weightLogId")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.modal_bottom_sheet_delete_log_entry, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deleteButton = view.findViewById<Button>(R.id.deleteLogButton)

        deleteButton.setOnClickListener {
            Log.i("TESTLOG", "DELETE LOG")
            if(foodLogId != null && foodLogId != 0)
                databaseViewModel.deleteFoodLog(foodLogId!!)
            if(weightLogId != null && weightLogId != 0)
                databaseViewModel.deleteWeightLog(weightLogId!!)

            dismiss()
        }



    }

    companion object {
        const val TAG = "ModalBottomSheet"

        fun newInstance(foodLogId: Int, weightLogId: Int) =
            ConfirmDeleteLogEntryBottomSheetDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt("foodLogId", foodLogId)
                    putInt("weightLogId", weightLogId)
                }
            }
    }
}
