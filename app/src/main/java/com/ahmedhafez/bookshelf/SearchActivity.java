package com.ahmedhafez.bookshelf;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout searchLayout;
    private ProgressBar progressBar;
    private EditText searchBar;
    private Button searchButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        searchLayout = findViewById(R.id.search_layout);
        progressBar = findViewById(R.id.progress_bar);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(this);
        searchBar = findViewById(R.id.search_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_button:
                String name = searchBar.getText().toString();
                searchWithName(name);
                break;
        }
    }

    private void searchWithName (String name) {
        searchLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        GetBooks booksTask = new GetBooks();
        booksTask.execute(name);

    }

    private class GetBooks extends AsyncTask<String, Void, List<Book>> {

        private BooksResponse bookResponse;
        private List<Book> books;
        private String name;

        @Override
        protected List<Book> doInBackground(String... params) {
            name = params[0];

            RetrofitInterface apiCall = RetrofitBuilder.getRetrofit().create(RetrofitInterface.class);
            Call<BooksResponse> call = apiCall.getBooks("intitle:" + name);
            call.enqueue(new Callback<BooksResponse>() {
                @Override
                public void onResponse(Call<BooksResponse>call, Response<BooksResponse> response) {
                    bookResponse = response.body();
                    books = bookResponse.getBooks();
                    Gson gson = new Gson();
                    String data = gson.toJson(books);
                    Intent intent = new Intent(context, ShelfActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);
                    searchLayout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFailure(Call<BooksResponse>call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    searchLayout.setVisibility(View.VISIBLE);
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                    // show internet error message
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
