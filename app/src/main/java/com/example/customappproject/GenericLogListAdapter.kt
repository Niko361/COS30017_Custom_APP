package com.example.customappproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.format.DateTimeFormatter

class GenericLogListAdapter(private val logList: List<GenericLogEntry>): RecyclerView.Adapter<GenericLogListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.generic_log_layout, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = logList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = logList[position]
        holder.bind(item)
    }

    inner class ViewHolder(val v: View): RecyclerView.ViewHolder(v) {
        val dateText = v.findViewById<TextView>(R.id.dateText)
        val dataText = v.findViewById<TextView>(R.id.dataText)

        //Group(val id: Int, val group: String, val location: String, val type: String, val datetime: LocalDateTime)

        fun bind(item: GenericLogEntry) {
            dateText.text = item.datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            dataText.text = item.value
        }
    }
}