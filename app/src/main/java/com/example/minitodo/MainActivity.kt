package com.example.minitodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minitodo.adapter.TaskAdapter
import com.example.minitodo.data.TaskModel
import com.example.minitodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskAdapter.OnItemClickListener{
    //Activity Binding Object
    private lateinit var binding: ActivityMainBinding

    //TaskAdapter for rendering items in the recycler view
    private lateinit var adapter: TaskAdapter

    //Todo List Holder
    private val mutableList = mutableListOf<TaskModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = TaskAdapter(this)
        binding.taskRv.adapter = adapter

        //Add Task OnClickListener
        binding.btnAddTask.setOnClickListener {
            val desc =  binding.tlTask.editText?.text.toString()
            if(desc.isNotEmpty()){
                mutableList.add(TaskModel(false, desc))
                adapter.updateList(mutableList)
                binding.tlTask.editText?.setText("")
            }
        }
    }

    override fun onItemDeleted(item: TaskModel) {
        //On Item Deleted Callback
        mutableList.remove(item)
        adapter.updateList(mutableList)
    }

    override fun onItemChecked(item: TaskModel) {
        //On Item Checked Callback
        val checked  = item.done
        mutableList.find { it.description == item.description }?.done = !checked
        adapter.updateList(mutableList)

    }


}