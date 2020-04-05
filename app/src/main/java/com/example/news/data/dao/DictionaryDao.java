package com.example.news.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.news.data.entity.DictionaryEntity;

import java.util.List;

@Dao
public interface DictionaryDao {

    @Insert
    void save(DictionaryEntity dictionaryEntity);

    @Insert
    void  save(DictionaryEntity... dictionaries);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<DictionaryEntity> dictionaryEntityList);

    @Delete
    void delete(DictionaryEntity dictionaryEntity);

    @Update
    void update(DictionaryEntity dictionaryEntity);

    @Query("SELECT * FROM dictionary ORDER BY id ASC")
    List<DictionaryEntity> getDictionary();

    @Query("SELECT * FROM dictionary WHERE id =:id")
    DictionaryEntity getDictionaries(int id);
}

