package com.example.customappproject

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.core.content.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationBarView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogWeightFragment: Fragment(R.layout.fragment_log_weight){
    private val databaseViewModel: DatabaseViewModel by activityViewModels()

    //private val databaseViewModel: DatabaseViewModel by viewModels {
    //    WordViewModelFactory((application as CatApplication).repository)
    //}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseViewModel.getAllWeightLogsForCat(1).observe(viewLifecycleOwner) { weightLogs ->
            Log.i("TESTLOG", weightLogs.toString())
            drawRecyclerView(weightLogs)

        }

        //drawRecyclerView()

        var weightEntryWeight: Double = 0.0


        val logWeightButton = view.findViewById<Button>(R.id.logWeightButton)

        logWeightButton.setOnClickListener {
            //weightLogs.add(GenericLogEntry(LocalDateTime.now(), "${weightEntryWeight}Kg"))
            //drawRecyclerView()
            // close keyboard after entering weight
            //val view: View? = this.currentFocus
            if (view != null) {
                val inputMethodManager = requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
            }
        }

        val enterWeigh = view.findViewById<EditText>(R.id.enterWeigh)
        enterWeigh.addTextChangedListener {
            weightEntryWeight = if(enterWeigh.text.isNullOrEmpty())
                0.0
            else
                enterWeigh.text.toString().toDouble()

            //don't allow the user to log zero grams of food
            logWeightButton.isEnabled = (weightEntryWeight != 0.0)
        }

    }

    fun drawRecyclerView(input: List<WeightLog>)
    {
        //weightLogs.sortByDescending{it.datetime}
        val list = view?.findViewById<RecyclerView>(R.id.genericLogRecyclerView)
        list?.adapter = GenericLogListAdapter(input)
        list?.layoutManager = LinearLayoutManager(requireContext())
    }
}