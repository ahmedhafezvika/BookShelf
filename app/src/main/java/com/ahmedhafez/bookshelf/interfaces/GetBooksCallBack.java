package com.ahmedhafez.bookshelf.interfaces;

import com.ahmedhafez.bookshelf.models.Book;

import java.util.List;

/**
 * Created by Eng.Ahmed on 4/16/2018.
 */

public interface GetBooksCallBack {

    void handleBooksResponse (List<Book> books);

    void handleBooksFailure (String error);
}
