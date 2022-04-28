package com.example.project1.ui.main

import android.app.Application
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import com.example.project1.base.ParentViewModel
import com.example.project1.data.models.CatInfo
import com.example.project1.data.models.CatInfoRoom
import com.example.project1.room.CatsDatabase
import com.example.project1.room.CatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : ParentViewModel() {

    var readAllData: LiveData<List<CatInfoRoom>>
    private val repository: CatsRepository

    // создает дб если не создана
    init {
        val todoDao = CatsDatabase.getCatsDatabase(app).catsDao()
        repository = CatsRepository(todoDao)
        readAllData = repository.getAllData
    }

    private val _open = MutableLiveData<Unit>()
    val open: LiveData<Unit>
        get() = _open

    init {
        _open.postValue(Unit)
    }

    // добавление в дб
    fun addToDatabase(item: CatInfo?) {
        if (item != null) {
            // делаем так птму что в главном треде нельзя и юзаем viewModelScope.launch
            viewModelScope.launch(Dispatchers.IO) {
                repository.addCat(
                    CatInfoRoom(
                        id = item.id!!,
                        url = item.url,
                        width = item.width,
                        height = item.height
                    )
                )
            }
        }
    }

    fun clearHistory() {
        // очистка дб
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearDatabase()
        }
    }

    override fun handleError(e: Throwable) {

    }
}