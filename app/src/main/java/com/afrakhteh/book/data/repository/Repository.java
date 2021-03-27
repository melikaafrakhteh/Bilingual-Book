package com.afrakhteh.book.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.afrakhteh.book.data.database.BookDao;
import com.afrakhteh.book.data.database.BookDataBase;
import com.afrakhteh.book.data.entities.BookModel;

import java.util.List;

public class Repository {
    private BookDao dao;
    private LiveData<List<BookModel>> model;

    public Repository(Application application) {
        BookDataBase db = BookDataBase.getInstance(application);
        dao = db.dao();
        model = dao.getAllPart();
    }

    public LiveData<List<BookModel>> getAllPart() {
        return model;
    }

    public void insert(BookModel book) {
        new insertAsyncTask(dao).execute(book);
    }

    private class insertAsyncTask extends AsyncTask<BookModel, Void, Void> {

        private BookDao asyncDao;

        public insertAsyncTask(BookDao d) {
            asyncDao = d;
        }

        @Override
        protected Void doInBackground(BookModel... bookModels) {
            asyncDao.insert(bookModels[0]);
            return null;
        }
    }
}
