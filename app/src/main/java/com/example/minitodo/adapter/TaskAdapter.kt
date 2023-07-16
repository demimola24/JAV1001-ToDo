package com.example.minitodo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minitodo.R
import com.example.minitodo.data.TaskModel


class TaskAdapter(private val delegate: OnItemClickListener) :
    RecyclerView.Adapter<TaskAdapter.TasksViewHolder>() {

    private var tasks: ArrayList<TaskModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bindView(tasks[position], delegate)
    }

    override fun getItemCount() = tasks.size

    inner class TasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: TaskModel, delegate: OnItemClickListener) {
            val textView = itemView.findViewById(R.id.txtName) as TextView
            textView.text= item.description

            val checkbox = itemView.findViewById(R.id.checkBox) as CheckBox
            checkbox.isChecked= item.done
            checkbox.setOnClickListener {
                delegate.onItemChecked(item)
            }

            val deleteButton = itemView.findViewById(R.id.txtDelete) as TextView
            deleteButton.setOnClickListener {
                delegate.onItemDeleted(item)
            }

        }

    }


    //Actions Delegate or Callback
    interface OnItemClickListener {
        fun onItemDeleted(item: TaskModel)
        fun onItemChecked(item: TaskModel)
    }

    //Update List Items
    fun updateList(list: List<TaskModel>) {
        tasks.clear()
        tasks.addAll(list)
        notifyDataSetChanged()
    }
}