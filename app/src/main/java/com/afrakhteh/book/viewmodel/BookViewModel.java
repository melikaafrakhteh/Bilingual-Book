package com.afrakhteh.book.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.afrakhteh.book.data.entities.BookModel;
import com.afrakhteh.book.data.repository.Repository;

import java.util.List;

public class BookViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<BookModel>> getAllPart;


    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        getAllPart = repository.getAllPart();
    }

    public LiveData<List<BookModel>> getGetAllPart(){
        return getAllPart;
    }
    public void insert(BookModel model){
        repository.insert(model);
    }
}
