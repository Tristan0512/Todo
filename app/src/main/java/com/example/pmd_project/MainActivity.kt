package com.example.pmd_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        val rvTodoItems = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvTodoItems)
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)
        val btnAddTodo = findViewById<Button>(R.id.btnAdd)

        btnAddTodo.setOnClickListener{
            val etTodoTitle = findViewById<EditText>(R.id.etTodoTitle)
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = TDL(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }
        val btnDeleteDoneTodo = findViewById<Button>(R.id.btnRmv)
        btnDeleteDoneTodo.setOnClickListener{
            todoAdapter.deleteDoneTodo()
        }
    }
}
