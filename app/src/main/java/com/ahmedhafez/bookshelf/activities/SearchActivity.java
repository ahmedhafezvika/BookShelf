package com.ahmedhafez.bookshelf.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

import com.ahmedhafez.bookshelf.interfaces.GetBooksCallBack;
import com.ahmedhafez.bookshelf.models.Book;
import com.ahmedhafez.bookshelf.models.BooksResponse;
import com.ahmedhafez.bookshelf.R;
import com.ahmedhafez.bookshelf.retrofit.RetrofitBuilder;
import com.ahmedhafez.bookshelf.retrofit.RetrofitInterface;
import com.ahmedhafez.bookshelf.utilities.RequestHandler;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, GetBooksCallBack{

    private LinearLayout searchLayout;
    private ProgressBar progressBar;
    private EditText searchBar;
    private Button searchButton;
    private Context context;
    private String criteria;
    private String hint;
    private RequestHandler requestHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        requestHandler = new RequestHandler(this);
        criteria = "intitle:";
        hint = "Book Title";

        searchLayout = findViewById(R.id.search_layout);
        progressBar = findViewById(R.id.progress_bar);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(this);
        searchBar = findViewById(R.id.search_bar);
        searchBar.setHint(hint);
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
        if (id == R.id.action_title) {
            criteria = "intitle:";
            hint = "Book Title";
            searchBar.setHint(hint);
            return true;
        }
        else if (id == R.id.action_isbn) {
            criteria = "isbn:";
            hint = "Book ISBN";
            searchBar.setHint(hint);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_button:
                String input = searchBar.getText().toString();
                searchBooks(input);
                break;
        }
    }

    private void searchBooks (String input) {
        searchLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        requestHandler.searchWithCriteria(criteria, input);
    }

    @Override
    public void handleBooksResponse(List<Book> books) {
        Gson gson = new Gson();
        String data = gson.toJson(books);
        Intent intent = new Intent(context, ShelfActivity.class);
        intent.putExtra("data", data);
        startActivity(intent);
        progressBar.setVisibility(View.GONE);
        searchLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void handleBooksFailure(String error) {
        progressBar.setVisibility(View.GONE);
        searchLayout.setVisibility(View.VISIBLE);
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
    }
}
