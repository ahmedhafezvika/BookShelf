package com.ahmedhafez.bookshelf;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ShelfActivity extends AppCompatActivity implements BooksAdapter.BooksAdapterOnClickHandler{

    private RecyclerView booksGrid;
    private BooksAdapter adapter;
    private List<Book> booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        booksGrid = findViewById(R.id.books_shelf);

        int cols = this.getResources().getInteger(R.integer.grid_cols);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, cols);
        booksGrid.setLayoutManager(gridLayoutManager);
        String data = getIntent().getStringExtra("data");
        Gson gson = new Gson();
        Type typeToken = new TypeToken<List<Book>>(){}.getType();
        booksList = gson.fromJson(data, typeToken);
        adapter = new BooksAdapter(this, this, booksList);
        booksGrid.setAdapter(adapter);
    }

    @Override
    public void onClick(Book book) {
        Gson gson = new Gson();
        String data = gson.toJson(book);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }
}
