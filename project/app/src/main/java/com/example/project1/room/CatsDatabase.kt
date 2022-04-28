package com.example.project1.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project1.data.models.CatInfoRoom

@Database(entities = [CatInfoRoom::class], version = 1, exportSchema = false)
abstract class CatsDatabase: RoomDatabase() {

    // создаем инстанс датабэйза
    abstract fun catsDao(): CatDao

    companion object{
        @Volatile
        private var INSTANCE: CatsDatabase? = null

        fun getCatsDatabase(context: Context): CatsDatabase {
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatsDatabase::class.java,
                    "cats_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}