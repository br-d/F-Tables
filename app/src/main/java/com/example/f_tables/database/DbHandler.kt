package com.example.f_tables.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.f_tables.*
import com.example.f_tables.model.Task
import timber.log.Timber

class DbHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableForTasks = "CREATE TABLE $TASK_TABLE " +
            "($ID Integer PRIMARY KEY AUTOINCREMENT, $TASK_TITLE TEXT)"
        db?.execSQL(createTableForTasks)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertTask(task: Task): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TASK_TITLE, task.title)
        val success = db.insert(TASK_TABLE, null, values)
        db.close()
        Timber.v("Inserted ID: $success")
        return (Integer.parseInt("$success") != -1)
    }

    fun selectAllTasksTitles(): List<String> {
        var allTasks : List<String> = mutableListOf()
        val db = readableDatabase
        val selectAllQuery = "SELECT * FROM $TASK_TABLE"
        val cursor = db.rawQuery(selectAllQuery, null)
        if(cursor != null){
            if (cursor.moveToFirst()){
                do{
                    var taskTitle = cursor.getString(cursor.getColumnIndex(TASK_TITLE))
                    allTasks += taskTitle
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return allTasks
    }
}