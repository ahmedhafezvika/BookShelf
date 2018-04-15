package com.ahmedhafez.bookshelf.models;

import com.ahmedhafez.bookshelf.models.Book;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Eng.Ahmed on 4/15/2018.
 */

public class BooksResponse {

    @SerializedName("items")
    private List<Book> books;

    public BooksResponse(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }
}
