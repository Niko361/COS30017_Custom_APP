package com.example.customappproject


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import java.time.format.DateTimeFormatter

class GenericLogListAdapter(private val logList: List<*>, private val fragmentManager: FragmentManager): RecyclerView.Adapter<GenericLogListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.generic_log_layout, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = logList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = logList[position]
        if(item is FoodLog)
            holder.bind(item)
        else if(item is WeightLog)
            holder.bind(item)

        holder.itemView.setOnClickListener {
            Log.i("TESTLOG", "CLICK")
        }


        holder.itemView.setOnLongClickListener {
            //Log.i("TESTLOG", "LONG CLICK")
            Log.i("TESTLOG", item.toString())

            var foodLogId = 0
            var weightLogId = 0

            if(item is FoodLog)
                foodLogId = item.id
            if(item is WeightLog)
                weightLogId = item.id

            val modalBottomSheet = ConfirmDeleteLogEntryBottomSheetDialogFragment.newInstance(foodLogId,weightLogId)
            modalBottomSheet.show(fragmentManager, ConfirmDeleteLogEntryBottomSheetDialogFragment.TAG)

            true
        }
    }

    inner class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        val dateText = v.findViewById<TextView>(R.id.dateText)
        val dataText = v.findViewById<TextView>(R.id.dataText)

        val genericLogLayout = v.findViewById<ConstraintLayout>(R.id.genericLogLayout)

        //Group(val id: Int, val group: String, val location: String, val type: String, val datetime: LocalDateTime)

        fun bind(item: FoodLog) {
            dateText.text = item.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"))
            dataText.text = "${item.calories} Cal"

        }

        fun bind(item: WeightLog) {
            dateText.text = item.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            dataText.text = "${"%.2f".format(item.catWeightGrams.toDouble()/1000)} Kg"
        }



    }
}