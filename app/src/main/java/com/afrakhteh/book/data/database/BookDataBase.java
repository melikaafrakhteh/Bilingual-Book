package com.afrakhteh.book.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.afrakhteh.book.data.entities.BookModel;

@Database(entities = {BookModel.class}, version = 1, exportSchema = false)
public abstract class BookDataBase extends RoomDatabase {
    public abstract BookDao dao();


    //volatile means that the variable changes at runtime and that the compiler should not cache its value for any reason.
    public static volatile BookDataBase INSTANCE;

    public static BookDataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookDataBase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BookDataBase.class, "BookDB.db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
