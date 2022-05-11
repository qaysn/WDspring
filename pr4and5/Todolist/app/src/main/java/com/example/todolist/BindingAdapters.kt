
package com.example.todolist
import android.view.View

import android.widget.ImageView

import androidx.databinding.BindingAdapter

import androidx.recyclerview.widget.RecyclerView

import com.example.todolist.network.Todos

import com.example.todolist.ui.TodolistApiStatus
import com.example.todolist.ui.TodolistListAdapter

/**
 * Updates the data shown in the [RecyclerView]
 */
@BindingAdapter("listData")
/// tut nado posmotret v LIST<TODOLIST>
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Todos>?) {
    val adapter = recyclerView.adapter as TodolistListAdapter
    adapter.submitList(data)
}

/**
 * This binding adapter displays the [TodoApiStatus] of the network request in an image view.
 * When the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: TodolistApiStatus?) {


    when(status) {
        TodolistApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        TodolistApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        TodolistApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}



