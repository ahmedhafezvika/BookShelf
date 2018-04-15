package com.ahmedhafez.bookshelf;

import java.util.List;

/**
 * Created by Eng.Ahmed on 4/15/2018.
 */

public class Book {
    private String id;
    private String title;
    private String publisher;
    private String publishDate;
    private String description;
    private String thumbnail;
    private String language;
    private String previewLink;
    private int pageCount, ratingCount, averageRating;
    private boolean pdfAvailable;
    private List<String> authors;
    private List<String> categories;
}
