package com.example.todo.data

import androidx.room.Embedded
import androidx.room.Relation

data class ItemAndCategory(
    @Embedded val item: Item,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val category: Category
)