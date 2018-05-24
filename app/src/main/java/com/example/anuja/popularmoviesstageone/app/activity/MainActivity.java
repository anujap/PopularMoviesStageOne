package com.example.anuja.popularmoviesstageone.app.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.anuja.popularmoviesstageone.R;
import com.example.anuja.popularmoviesstageone.app.adapters.MovieGridAdapter;
import com.example.anuja.popularmoviesstageone.common.SortMovie;
import com.example.anuja.popularmoviesstageone.model.MovieDetails;
import com.example.anuja.popularmoviesstageone.viewmodel.MainViewModel;

import java.util.List;

/**
 * This class displays the movies in a grid format based on
 * menu selection - Popular/Top Rated.
 *
 * References:- https://stackoverflow.com/questions/33575731/gridlayoutmanager-how-to-auto-fit-columns
 */
public class MainActivity extends BaseActivity implements MovieGridAdapter.GridItemClickListener {

    protected static final String MOVIE_DETAIL_ITEM = "movie_detail_item";
    private static final String SORT_OPTION = "sort";

    // toolbar
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MovieGridAdapter movieGridAdapter;

    // viewmodel
    private MainViewModel mainViewModel;

    private String sortMovie = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null && savedInstanceState.containsKey(SORT_OPTION)) {
            sortMovie = String.valueOf(savedInstanceState.get(SORT_OPTION));
        }

        // get the viewmodel
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        setUpToolBar();
        setUpRecyclerView();

    }

    /**
     * Function called to set up the toolbar
     */
    private void setUpToolBar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(R.string.action_itm_pop_mv);
        setSupportActionBar(toolbar);
    }

    /**
     * Function called to set up the recycler view and display
     * the list of movies
     */
    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        movieGridAdapter = new MovieGridAdapter(null, this);
        recyclerView.setAdapter(movieGridAdapter);

        /**
         * Ref:- https://stackoverflow.com/questions/29579811/changing-number-of-columns-with-gridlayoutmanager-and-recyclerview
         */
        recyclerView.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        if(sortMovie == null || TextUtils.equals(sortMovie, SortMovie.POPULAR.name()))
            menu.getItem(0).setChecked(true);
        else
            menu.getItem(1).setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.action_itm_popular_mv:
                sortMovie = SortMovie.POPULAR.name();
                displayMoviesOnMenuSelection(item, mainViewModel.getPopularMoviesList().getValue());
                return true;
            case R.id.action_itm_top_rated_mv:
                sortMovie = SortMovie.TOP_RATED.name();
                displayMoviesOnMenuSelection(item, mainViewModel.getTopRatedMoviesList().getValue());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Function called to display the list of movies based on the menu
     * selection - Popular/Top Rated
     * @param item - Menu item
     * @param movies - list of movies (Popular/Top Rated)
     */
    private void displayMoviesOnMenuSelection(MenuItem item, List<MovieDetails> movies) {
        item.setChecked(item.isChecked() ? false : true);
        toolbar.setTitle(item.getTitle().toString());
        movieGridAdapter.swapLists(movies);
    }

    /**
     * function called to get the movies based on the sort order
     * Popular/top rated
     */
    private void retrieveQueriedMovies() {
        mainViewModel.displayMovies();

        mainViewModel.getTopRatedMoviesList().observe(this, movieDetails -> {
            if(TextUtils.equals(sortMovie, SortMovie.TOP_RATED.name()))
                movieGridAdapter.swapLists(movieDetails);
        });

        mainViewModel.getPopularMoviesList().observe(this, movieDetails -> {
            if(TextUtils.equals(sortMovie, SortMovie.POPULAR.name()))
                movieGridAdapter.swapLists(movieDetails);
        });
    }


    /**
     * Function called when the grid item is clicked.
     */
    @Override
    public void onGridItemClick(MovieDetails movie) {
        //click event when an item is clicked
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(MOVIE_DETAIL_ITEM, movie);
        startActivity(intent);
    }

    /**
     * Function called when the connection is available
     */
    @Override
    protected void onConnected() {
        retrieveQueriedMovies();
    }

    /**
     * Function called when the connection is not available
     */
    @Override
    protected void onDisconnected() {}


    /**
     * function called to auto fit the number of columns in a grid
     */
    private int calculateNoOfColumns() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(SORT_OPTION, sortMovie);
    }
}
