package com.ahmedhafez.bookshelf;

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

import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText searchBar;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
                break;
        }
    }

    private void searchWithName (String name) {

    }

    private class GetShowsTask extends AsyncTask<String, Void, List<TvShow>> {

        private ShowsResponse showsResponse;
        private List<TvShow> shows;
        private String sortType;
        private String key;
        private int pageNum;

        @Override
        protected List<TvShow> doInBackground(String... params) {
            sortType = params[0];
            key = params[1];
            pageNum = Integer.parseInt(params[2]);

            RetrofitInterface apiCall = RetrofitBuilder.getRetrofit().create(RetrofitInterface.class);
            Call<ShowsResponse> call = apiCall.getShows(sortType, key, pageNum);
            call.enqueue(new Callback<ShowsResponse>() {
                @Override
                public void onResponse(Call<ShowsResponse>call, Response<ShowsResponse> response) {
                    showsResponse = response.body();
                    UtilsMethods.setTotalPages(sortType, showsResponse.getTotalPages());
                    shows = showsResponse.getShows();
                    showsReceiver.handleGetShowsCallbackResponse(shows, sortType);
                }

                @Override
                public void onFailure(Call<ShowsResponse>call, Throwable t) {
                    // show internet error message
                }
            });
            return shows;
        }

        @Override
        protected void onPostExecute(List<TvShow> shows) {
            // notify
        }
    }
}
