package com.example.project1.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cats_table1")
data class CatInfoRoom(
    @PrimaryKey val id: String,
    val url: String?,
    val width: Int?,
    val height: Int?
)