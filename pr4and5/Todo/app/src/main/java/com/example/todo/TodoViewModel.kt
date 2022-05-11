package com.example.todo

import androidx.lifecycle.*
import com.example.todo.data.Item
import com.example.todo.data.ItemDao
import kotlinx.coroutines.launch


class TodoViewModel(private val itemDao: ItemDao) : ViewModel() {

    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()



    fun updateItem(
        itemId: Int,
        itemTitle: String,
        itemDescription: String,
        itemStatus: String,
        itemCategory: String,
        itemDuration: String,


        ) {
        val updatedItem = getUpdatedItemEntry(itemId, itemTitle, itemDescription, itemStatus,itemCategory,itemDuration)
        updateItem(updatedItem)
    }


    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }



    fun addNewItem(itemTitle: String, itemDescription: String, itemStatus: String,itemCategory: String,itemDuration: String) {
        val newItem = getNewItemEntry(itemTitle, itemDescription, itemStatus,itemCategory,itemDuration)
        insertItem(newItem)
    }

    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    fun retrieveItem(id: Int): LiveData<Item> {
        return itemDao.getItem(id).asLiveData()
    }

    fun isEntryValid(itemTitle: String, itemDescription: String, itemStatus: String,itemCategory: String,itemDuration: String): Boolean {
        if (itemTitle.isBlank() || itemDescription.isBlank() ||  itemStatus.isBlank() ||  itemCategory.isBlank() ||  itemDuration.isBlank()) {
            return false
        }
        return true
    }

    private fun getNewItemEntry(itemTitle: String, itemDescription: String, itemStatus: String,itemCategory: String,itemDuration: String): Item {
        return Item(

            itemTitle = itemTitle,
            itemDescription = itemDescription,
            itemStatus = itemStatus,
            itemCategory = itemCategory,
            itemDuration = itemDuration
        )
    }

    private fun getUpdatedItemEntry(
        itemId: Int,
        itemTitle: String,
        itemDescription: String,
        itemStatus: String,
        itemCategory: String,
        itemDuration: String,
    ): Item {
        return Item(
            id = itemId,
            itemTitle = itemTitle,
            itemDescription = itemDescription,
            itemStatus = itemStatus,
            itemCategory = itemCategory,
            itemDuration = itemDuration
        )
    }


}