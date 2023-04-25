package com.example.pmd_project

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter (
    private val todos: MutableList<TDL>
) :RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: TDL)
    {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)

    }

    fun deleteDoneTodo(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if (isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        val buton = holder.itemView.findViewById<CheckBox>(R.id.checked)
        val titlu = holder.itemView.findViewById<TextView>(R.id.tvTodoTitle)
        titlu.text = curTodo.title
        buton.isChecked = curTodo.isChecked
        toggleStrikeThrough(titlu, curTodo.isChecked)
        buton.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(titlu, isChecked)
            curTodo.isChecked = !curTodo.isChecked
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}



