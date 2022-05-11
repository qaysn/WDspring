
package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.todolist.network.Todos
import com.example.todolist.network.TodosApi

import kotlinx.coroutines.launch

enum class TodolistApiStatus {LOADING, ERROR, DONE}

class TodolistViewModel : ViewModel() {

    private val _status = MutableLiveData<TodolistApiStatus>()
     val status : LiveData<TodolistApiStatus> = _status
    // _todos                Todos
    private val _todos = MutableLiveData<List<Todos>>()
    val todos : LiveData<List<Todos>> = _todos

    private val _todo = MutableLiveData<Todos>()
    val todo : LiveData<Todos> = _todo
    // getTodosList
    fun getTodosList() {
        viewModelScope.launch {
            _status.value = TodolistApiStatus.LOADING
            try{
                _todos.value = TodosApi.retrofitService.getTodos()
                    _status.value = TodolistApiStatus.DONE

            }   catch (e: Exception){
                _status.value = TodolistApiStatus.ERROR
                _todos.value = listOf()
            }
        }
    }

    fun onTodolistClicked(todo: Todos) {
        _todo.value = todo


    }
}
