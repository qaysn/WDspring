
package com.example.todolist.network

/**
 * This data class defines an Amphibian which includes the amphibian's name, the type of
 * amphibian, and a brief description of the amphibian.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class Todos(
    // tut ubrat i dobavit userId, id, title, completed
    val id : Int,
    val userId:Int,
    val title: String,

    val completed: Boolean
)
