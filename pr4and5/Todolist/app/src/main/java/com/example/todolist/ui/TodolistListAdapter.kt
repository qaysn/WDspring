
package com.example.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView



import com.example.todolist.databinding.ListViewItemBinding
import com.example.todolist.network.Todos


class TodolistListAdapter(private val clickListener: TodolistListener) :
    // // zdes na Todos                        zdes TodosViewHolder
    ListAdapter<Todos, TodolistListAdapter.TodosViewHolder>(DiffCallback) {
    //zdes toje
    class TodosViewHolder(
        var binding: ListViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root){
        // zdes todos: Todos
        fun bind(clickListener: TodolistListener, todos: Todos) {
            //todos
            binding.amphibian = todos
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
    //todos
    companion object DiffCallback : DiffUtil.ItemCallback<Todos>() {
        //pomenyat name na title todos
        override fun areItemsTheSame(oldItem: Todos, newItem: Todos): Boolean {
            return oldItem.title == newItem.title
        }
        //pomenyat name na title
        override fun areContentsTheSame(oldItem: Todos, newItem: Todos): Boolean {
            return oldItem.title == newItem.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TodosViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        val todos = getItem(position)
        holder.bind(clickListener, todos)
    }
}

class TodolistListener(val clickListener: (todos: Todos) -> Unit) {
    fun onClick(todos: Todos) = clickListener(todos)
}
