package com.example.todo;
import android.app.Application;
import androidx.room.Room;

public class MyApplication extends Application {
    //MyApplication следить за всем
    //чтобы не создавать постоянный конэкшн мы используем singleTon
    public static MyApplication instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        //инициализация
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "todo_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        //Эта наша таблица категории, откуда мы будем брать айдишку и тайтл категории для тудулиста
        try {
            database.categoryDao().insert(new Category("Important"));
            database.categoryDao().insert(new Category("Not important"));
            database.categoryDao().insert(new Category("University"));
            database.categoryDao().insert(new Category("Work"));
            database.categoryDao().insert(new Category("Cook"));
            database.categoryDao().insert(new Category("Self development"));
            database.categoryDao().insert(new Category("Another"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}