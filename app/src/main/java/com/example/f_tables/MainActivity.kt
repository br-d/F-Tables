package com.example.f_tables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.f_tables.databinding.ActivityMainBinding
import com.example.f_tables.model.Task

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val createNewTaskButton = binding.createTaskButton

        createNewTaskButton.setOnClickListener {
            val newTask = createNewTask(binding.newTaskTextField.text.toString())
            Log.i("MainActivity", "Created new Task with title " + newTask.title)
        }
    }

    private fun createNewTask(taskName: String) : Task{
        return Task(taskName)
    }
}