
package com.example.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val itemTitle: String,
    @ColumnInfo(name = "description")
    val itemDescription: String,
    @ColumnInfo(name = "status")
    val itemStatus: String,
    @ColumnInfo(name = "category")
    val itemCategory: String,
    @ColumnInfo(name = "duration")
    val itemDuration: String )

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Item::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("categoryId"),
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    primaryKeys = ["categoryId"])
data class Category(
    @PrimaryKey
    val categoryId : Int,
    val categoryTitle : String,)
