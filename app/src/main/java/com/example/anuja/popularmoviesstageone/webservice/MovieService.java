package com.example.anuja.popularmoviesstageone.webservice;

import com.example.anuja.popularmoviesstageone.model.MoviePage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("popular")
    Call<MoviePage> getPopularMovies(@Query("api_key") String apiKey);

    @GET("top_rated")
    Call<MoviePage> getTopRatedMovies(@Query("api_key") String apiKey);

}
