
package com.example.todolist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

import com.example.todolist.R
import com.example.todolist.databinding.FragmentTodolistListBinding



class TodolistListFragment : Fragment() {

    private val viewModel: TodolistViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTodolistListBinding.inflate(inflater)

        // zdes getTodosList()
        viewModel.getTodosList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = TodolistListAdapter(TodolistListener { todolist ->
            viewModel.onTodolistClicked(todolist)
            findNavController()
                .navigate(R.id.action_todolistListFragment_to_todolistDetailFragment)
        })


        return binding.root
    }
}
