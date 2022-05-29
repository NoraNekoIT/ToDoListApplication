package com.noranekoit.submission.todolistapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noranekoit.submission.todolistapplication.R
import com.noranekoit.submission.todolistapplication.data.ToDo

class ListTodoAdapter(private val listTodo: ArrayList<ToDo>):RecyclerView.Adapter<ListTodoAdapter.ListViewHolder>() {

    private lateinit var mListener: OnItemClickListeners

    interface OnItemClickListeners{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListeners){
        mListener = listener
    }

    fun updateItem(i: Int, pesan: String){
        listTodo[i] = ToDo(pesan)
        notifyDataSetChanged()
    }

    fun deleteItem(i: Int){
        listTodo.removeAt(i)
        notifyDataSetChanged()
    }

    fun addItem(i: Int, todo: ToDo){
        listTodo.add(i,todo)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        var tvNote: TextView =itemView.findViewById(R.id.tv_note)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false)
        return  ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (note) = listTodo[position]
        holder.tvNote.text = note

        holder.itemView.setOnClickListener{
            mListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int =listTodo.size

}