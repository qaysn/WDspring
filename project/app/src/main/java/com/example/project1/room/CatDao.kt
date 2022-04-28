package com.example.project1.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.project1.data.models.CatInfoRoom

@Dao
interface CatDao {
// SQL запросы для рума
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(catInfoRoom: CatInfoRoom)

    @Query("SELECT * FROM cats_table1")
    fun getAllData(): LiveData<List<CatInfoRoom>>

    @Query("DELETE FROM cats_table1")
    fun clearDatabase()
}