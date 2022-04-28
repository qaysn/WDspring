package com.example.project1.room

import androidx.lifecycle.LiveData
import com.example.project1.data.models.CatInfoRoom

class CatsRepository(private val catsDao: CatDao) {

    // repo for room db with Dao
    val getAllData: LiveData<List<CatInfoRoom>> = catsDao.getAllData()

    suspend fun addCat(catsItem: CatInfoRoom){
        catsDao.addTodo(catsItem)
    }

    fun clearDatabase(){
        catsDao.clearDatabase()
    }
}