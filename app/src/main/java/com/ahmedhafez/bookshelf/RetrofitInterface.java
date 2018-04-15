package com.ahmedhafez.bookshelf;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Eng.Ahmed on 4/15/2018.
 */

public interface RetrofitInterface {

    @GET("v1/volumes")
    Call<BooksResponse> getBooks(@Query("q") String name);
}
