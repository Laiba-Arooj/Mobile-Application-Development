package com.example.labterminal

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labterminal.databinding.ActivityMainBinding

class myViewHolder(itemView: View, listener:ItemAdapter.onItemClickListener): RecyclerView.ViewHolder(itemView) {

    var stdName = itemView.findViewById<TextView>(R.id.stdName)
    var regNo = itemView.findViewById<TextView>(R.id.regNo)
    var btn = itemView.findViewById<Button>(R.id.sendSmsBtn)

    init {
        btn.setOnClickListener(){
            listener.onItemClick(adapterPosition)
        }
    }
}