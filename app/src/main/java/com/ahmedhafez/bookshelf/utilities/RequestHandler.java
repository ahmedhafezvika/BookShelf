package com.ahmedhafez.bookshelf.utilities;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.ahmedhafez.bookshelf.activities.SearchActivity;
import com.ahmedhafez.bookshelf.activities.ShelfActivity;
import com.ahmedhafez.bookshelf.interfaces.GetBooksCallBack;
import com.ahmedhafez.bookshelf.models.Book;
import com.ahmedhafez.bookshelf.models.BooksResponse;
import com.ahmedhafez.bookshelf.retrofit.RetrofitBuilder;
import com.ahmedhafez.bookshelf.retrofit.RetrofitInterface;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eng.Ahmed on 4/16/2018.
 */

public class RequestHandler {

    private GetBooksCallBack receiver;

    public RequestHandler(GetBooksCallBack receiver) {
        this.receiver = receiver;
    }

    public void searchWithCriteria (String criteria, String input) {
        GetBooksTask booksTask = new GetBooksTask();
        booksTask.execute(criteria, input);

    }

    private class GetBooksTask extends AsyncTask<String, Void, List<Book>> {

        private BooksResponse bookResponse;
        private List<Book> books;
        private String input;
        private String criteria;

        @Override
        protected List<Book> doInBackground(String... params) {
            criteria = params[0];
            input = params[1];

            RetrofitInterface apiCall = RetrofitBuilder.getRetrofit().create(RetrofitInterface.class);
            Call<BooksResponse> call = apiCall.getBooks(criteria + input);
            call.request().url();
            call.enqueue(new Callback<BooksResponse>() {
                @Override
                public void onResponse(Call<BooksResponse>call, Response<BooksResponse> response) {
                    bookResponse = response.body();
                    books = bookResponse.getBooks();
                    receiver.handleBooksResponse(books);
                }

                @Override
                public void onFailure(Call<BooksResponse>call, Throwable t) {
                    receiver.handleBooksFailure(t.getMessage());
                }
            });
            return books;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            // notify
        }
    }
}
