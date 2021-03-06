package com.ahmedhafez.bookshelf.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng.Ahmed on 4/15/2018.
 */

public class Book {

    @SerializedName("id")
    private String id;

    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;

    public Book(String id, VolumeInfo volumeInfo) {
        this.id = id;
        this.volumeInfo = volumeInfo;
    }

    public String getId() {
        return id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
}
