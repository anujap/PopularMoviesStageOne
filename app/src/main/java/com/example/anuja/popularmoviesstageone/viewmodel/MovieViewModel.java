package com.example.anuja.popularmoviesstageone.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.anuja.popularmoviesstageone.model.MoviePage;

public class MovieViewModel extends ViewModel {

    private String movieId;
    private LiveData<MoviePage> moviePage;

    public void init(String movieId) {
        this.movieId = movieId;
    }

    public LiveData<MoviePage> getMoviePage() {
        return moviePage;
    }
}
