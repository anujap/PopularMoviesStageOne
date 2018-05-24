package com.example.anuja.popularmoviesstageone.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;
import android.util.Log;

import com.example.anuja.popularmoviesstageone.model.MovieDetails;
import com.example.anuja.popularmoviesstageone.model.MoviePage;
import com.example.anuja.popularmoviesstageone.webservice.MovieRetrofitClient;
import com.example.anuja.popularmoviesstageone.webservice.MovieUtils;
import com.example.anuja.popularmoviesstageone.webservice.MovieWebserviceInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private MovieWebserviceInterface movieWebservice = MovieRetrofitClient.getInstance().getMovieWebservice();

    private MutableLiveData<List<MovieDetails>> popularMoviesList;
    private MutableLiveData<List<MovieDetails>> topRatedMoviesList;

    // https://proandroiddev.com/refactor-with-the-new-viewmodel-class-b334fd88bf82
    public MutableLiveData<List<MovieDetails>> getPopularMoviesList() {
        if(popularMoviesList == null)
            popularMoviesList = new MutableLiveData<>();
        return popularMoviesList;
    }

    public MutableLiveData<List<MovieDetails>> getTopRatedMoviesList() {
        if(topRatedMoviesList == null)
            topRatedMoviesList = new MutableLiveData<>();
        return topRatedMoviesList;
    }

    /**
     * Function used to download the movies specific to the endpoints
     */
    public void displayMovies() {

        if(popularMoviesList == null) {
            movieWebservice.getMovies(MovieUtils.ENDPOINT_POPULARITY, MovieUtils.API_KEY).enqueue(new Callback<MoviePage>() {
                @Override
                public void onResponse(Call<MoviePage> call, Response<MoviePage> response) {
                    if(response.isSuccessful()) {

                        List<MovieDetails> movieList = response.body().getResults();
                        if(movieList != null && movieList.size() > 0) {
                            popularMoviesList.postValue(movieList);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MoviePage> call, Throwable t) {
                    Log.e(TAG, "Error retrieving the movies");
                }
            });
        }

        if(topRatedMoviesList == null) {
            movieWebservice.getMovies(MovieUtils.ENDPOINT_TOP_RATED, MovieUtils.API_KEY).enqueue(new Callback<MoviePage>() {
                @Override
                public void onResponse(Call<MoviePage> call, Response<MoviePage> response) {
                    if(response.isSuccessful()) {

                        List<MovieDetails> movieList = response.body().getResults();
                        if(movieList != null && movieList.size() > 0) {
                            topRatedMoviesList.postValue(movieList);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MoviePage> call, Throwable t) {
                    Log.e(TAG, "Error retrieving the movies");
                }
            });
        }
    }
}
