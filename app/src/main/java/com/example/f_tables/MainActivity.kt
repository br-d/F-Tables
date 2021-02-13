package com.example.f_tables

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.f_tables.database.DbHandler
import com.example.f_tables.databinding.ActivityMainBinding
import com.example.f_tables.model.Task
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val createNewTaskButton = binding.createTaskButton
        val dbHandler = DbHandler(this)

        val allActiveTasks = dbHandler.selectAllTasksTitles()
        for(task in allActiveTasks){
            val textView = TextView(this)
            textView.text = task
            textView.setTextAppearance(R.style.taskText)
            binding.contentContainer.addView(textView)
        }
        createNewTaskButton.setOnClickListener {
            val newTask = createNewTask(binding.newTaskTextField.text.toString())
            dbHandler.insertTask(newTask)
            Timber.i("Created new Task with title: %s", newTask.title)
            val textView = TextView(this)
            textView.text = newTask.title
            textView.setTextAppearance(R.style.taskText)
            binding.contentContainer.addView(textView)
        }
    }

    private fun createNewTask(taskName: String) : Task{
        return Task(taskName)
    }
}