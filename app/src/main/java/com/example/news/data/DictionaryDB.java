package com.example.news.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.news.data.dao.DictionaryDao;
import com.example.news.data.dao.UserDao;
import com.example.news.data.entity.DictionaryEntity;
import com.example.news.data.entity.User;

@Database(version = 3, entities = {User.class, DictionaryEntity.class})
abstract public class DictionaryDB extends RoomDatabase {

    private static final String DB_NAME = "dictionary_db";

    //create abstract method for dao
    public abstract UserDao userDao();
    public abstract DictionaryDao dictionaryDao();

    public static DictionaryDB getInstance(Context context) {
        return Room.databaseBuilder(context, DictionaryDB.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}
