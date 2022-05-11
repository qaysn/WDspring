
package com.example.todo

import android.app.Application
import com.example.todo.data.ItemRoomDatabase


class TodoApplication : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}
