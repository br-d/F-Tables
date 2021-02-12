package com.example.f_tables

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

        createNewTaskButton.setOnClickListener {
            val newTask = createNewTask(binding.newTaskTextField.text.toString())

            Timber.i("Created new Task with title: " + newTask.title)

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