package com.example.customappproject

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationBarView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LogWeightFragment: Fragment(R.layout.fragment_log_weight){
    var weightLogs = mutableListOf(
        GenericLogEntry(LocalDateTime.parse("22/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.04Kg"),
        GenericLogEntry(LocalDateTime.parse("21/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.10Kg"),
        GenericLogEntry(LocalDateTime.parse("20/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.18Kg"),
        GenericLogEntry(LocalDateTime.parse("19/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.20Kg"),
        GenericLogEntry(LocalDateTime.parse("18/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.22Kg"),
        GenericLogEntry(LocalDateTime.parse("17/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.25Kg"),
        GenericLogEntry(LocalDateTime.parse("16/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.30Kg"),
        GenericLogEntry(LocalDateTime.parse("15/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.33Kg"),
        GenericLogEntry(LocalDateTime.parse("14/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.35Kg"),
        GenericLogEntry(LocalDateTime.parse("13/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.38Kg"),
        GenericLogEntry(LocalDateTime.parse("12/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.40Kg"),
        GenericLogEntry(LocalDateTime.parse("11/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.44Kg"),
        GenericLogEntry(LocalDateTime.parse("10/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.45Kg"),
        GenericLogEntry(LocalDateTime.parse("09/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.5Kg"),
        GenericLogEntry(LocalDateTime.parse("23/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.55Kg"),
        GenericLogEntry(LocalDateTime.parse("22/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.60Kg"),
        GenericLogEntry(LocalDateTime.parse("21/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.60Kg"),
        GenericLogEntry(LocalDateTime.parse("20/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.62Kg"),
        GenericLogEntry(LocalDateTime.parse("19/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.62Kg"),
        GenericLogEntry(LocalDateTime.parse("18/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.64Kg"),
        GenericLogEntry(LocalDateTime.parse("17/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.65Kg"),
        GenericLogEntry(LocalDateTime.parse("16/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.66Kg"),
        GenericLogEntry(LocalDateTime.parse("15/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.67Kg"),
        GenericLogEntry(LocalDateTime.parse("14/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.70Kg"),
        GenericLogEntry(LocalDateTime.parse("13/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.71Kg"),
        GenericLogEntry(LocalDateTime.parse("12/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.73Kg"),
        GenericLogEntry(LocalDateTime.parse("11/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.74Kg"),
        GenericLogEntry(LocalDateTime.parse("10/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.75Kg"),
        GenericLogEntry(LocalDateTime.parse("09/09/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")), "6.75Kg"),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawRecyclerView()

        var weightEntryWeight: Double = 0.0


        val logWeightButton = view.findViewById<Button>(R.id.logWeightButton)

        logWeightButton.setOnClickListener {
            weightLogs.add(GenericLogEntry(LocalDateTime.now(), "${weightEntryWeight}Kg"))
            drawRecyclerView()
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

    fun drawRecyclerView()
    {
        weightLogs.sortByDescending{it.datetime}
        val list = view?.findViewById<RecyclerView>(R.id.genericLogRecyclerView)
        list?.adapter = GenericLogListAdapter(weightLogs)
        list?.layoutManager = LinearLayoutManager(requireContext())
    }
}