package com.example.anuja.popularmoviesstageone.app.activity;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.anuja.popularmoviesstageone.R;
import com.example.anuja.popularmoviesstageone.app.adapters.MovieGridAdapter;
import com.example.anuja.popularmoviesstageone.model.MovieDetails;

import java.util.List;

public class MainActivity extends BaseActivity implements MovieGridAdapter.GridItemClickListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MovieGridAdapter movieGridAdapter;

    private List<MovieDetails> mPopularMoviesList;
    private List<MovieDetails> mTopRatedMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        setSupportActionBar(toolbar);
        setUpRecyclerView();
    }

    /**
     * Function called to set up the recycler view and display
     * the list of movies
     */
    private void setUpRecyclerView() {
        movieGridAdapter = new MovieGridAdapter(null, this);
        recyclerView.setAdapter(movieGridAdapter);

        /**
         * Ref:- https://stackoverflow.com/questions/29579811/changing-number-of-columns-with-gridlayoutmanager-and-recyclerview
         */
        int span = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? 2: 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, span));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {
            case R.id.action_itm_popular_mv:
                return true;
            case R.id.action_itm_top_rated_mv:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Function called when the grid item is clicked.
     */
    @Override
    public void onGridItemClick(MovieDetails movie) {
        //click event when an item is clicked
    }
}
