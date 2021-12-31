package com.example.labterminal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
class ItemAdapter(private val names: ArrayList<String>, private val regNo: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listen:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_items,parent,false)
        return myViewHolder(view,listen)
    }

    fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.stdName.text =  names[position]
        holder.regNo.text = regNo[position]
    }

    override fun getItemCount(): Int {
        return names.size
    }

    fun setOnItemClickListener(listener:onItemClickListener){
        listen = listener
    }

    override fun onBindViewHolder(holder : RecyclerView.ViewHolder, position : Int) {
        TODO("Not yet implemented")
    }


}