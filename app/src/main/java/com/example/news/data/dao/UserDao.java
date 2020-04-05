package com.example.news.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.news.data.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void save(User user);

    @Insert
    void save(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<User> books);

    @Delete
    void delete(User user);

    @Update
    void update(User book);

    @Query("SELECT * FROM User where email= :email and password= :password")
    User getUser(String email, String password);
}
