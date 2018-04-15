package com.ahmedhafez.bookshelf.models;

/**
 * Created by Eng.Ahmed on 4/15/2018.
 */

import com.google.gson.annotations.SerializedName;

public class ImageLinks {

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("smallThumbnail")
    private String smallThumbnail;

    public ImageLinks(String thumbnail, String smallThumbnail) {
        this.thumbnail = thumbnail;
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }
}
