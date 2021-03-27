package com.afrakhteh.book.data.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.afrakhteh.book.data.entities.BookModel;

import java.util.List;

@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BookModel model);

    @Query("SELECT * FROM BookModel")
    LiveData<List<BookModel>> getAllPart();


}
