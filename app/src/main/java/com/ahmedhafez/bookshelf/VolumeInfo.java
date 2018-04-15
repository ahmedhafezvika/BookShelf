package com.ahmedhafez.bookshelf;

/**
 * Created by Eng.Ahmed on 4/15/2018.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VolumeInfo {

    @SerializedName("title")
    private String title;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("publishedDate")
    private String publishDate;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    @SerializedName("previewLink")
    private String previewLink;

    @SerializedName("pageCount")
    private int pageCount;

    @SerializedName("ratingsCount")
    private int ratingCount;

    @SerializedName("averageRating")
    private double averageRating;

    @SerializedName("authors")
    private List<String> authors;

    @SerializedName("categories")
    private List<String> categories;

    @SerializedName("industryIdentifiers")
    private List<IndustryIdentifiers> identifiers;

    @SerializedName("imageLinks")
    private ImageLinks imageLinks;

    public VolumeInfo(String title, String publisher, String publishDate, String description, String language,
                      String previewLink, int pageCount, int ratingCount, int averageRating,
                      List<String> authors, List<String> categories, ImageLinks imageLinks) {
        this.title = title;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.description = description;
        this.language = language;
        this.previewLink = previewLink;
        this.pageCount = pageCount;
        this.ratingCount = ratingCount;
        this.averageRating = averageRating;
        this.authors = authors;
        this.categories = categories;
        this.imageLinks = imageLinks;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }
}
