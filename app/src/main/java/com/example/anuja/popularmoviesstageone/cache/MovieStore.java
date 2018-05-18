package com.example.anuja.popularmoviesstageone.cache;

import com.example.anuja.popularmoviesstageone.model.MoviePage;
import com.example.anuja.popularmoviesstageone.model.MovieDetails;
import com.example.anuja.popularmoviesstageone.webservice.MovieRetrofitClient;
import com.example.anuja.popularmoviesstageone.webservice.MovieWebserviceInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class used to download and parse the API data.
 */
public class MovieStore {

    private MovieWebserviceInterface movieWebservice = MovieRetrofitClient.getInstance().getMovieWebservice();

    ArrayList<MovieDetails> movies;



}
