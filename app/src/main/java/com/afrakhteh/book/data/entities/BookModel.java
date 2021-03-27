package com.afrakhteh.book.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BookModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String image;
    private String title;
    private String description;

    public BookModel(int id, String image, String title, String description) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String  getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
