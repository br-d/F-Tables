package com.example.f_tables

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout.*
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.f_tables.database.DbHandler
import com.example.f_tables.databinding.ActivityMainBinding
import com.example.f_tables.model.Task
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
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
            val textViewParams: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
            )
            textViewParams.bottomMargin = 10
            textViewParams.topMargin = 10
            val textView = TextView(this)
            textView.text = task
            textView.setTextAppearance(R.style.taskText)
            textView.layoutParams = textViewParams
            textView.isClickable = true
            textView.setBackgroundColor(Color.parseColor("#F7D6D6"))
            textView.setOnClickListener{
                Timber.i("OnClickListenerForButtonWithTitle: ${textView.text}")
            }
            binding.contentContainer.addView(textView)
        }
        createNewTaskButton.setOnClickListener {
            val newTask = createNewTask(binding.newTaskTextField.text.toString())
            dbHandler.insertTask(newTask)
            Timber.i("Created new Task with title: " + newTask.title)

            val textViewParams: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT,
                    )
            textViewParams.bottomMargin = 10
            textViewParams.topMargin = 10
            val textView = TextView(this)
            textView.id = newTask.id
            textView.layoutParams = textViewParams
            textView.text = newTask.title
            textView.setTextAppearance(R.style.taskText)
            textView.isClickable = true
            textView.setBackgroundColor(Color.parseColor("#F7D6D6"))
            textView.setOnClickListener{
                Timber.i("OnClickListenerForButtonWithTitle: ${textView.text}")

            }
            binding.contentContainer.addView(textView)
        }
    }

    private fun createNewTask(taskName: String) : Task{
        return Task(taskName)
    }
}