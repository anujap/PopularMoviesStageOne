package com.example.anuja.popularmoviesstageone.webservice;

import com.example.anuja.popularmoviesstageone.model.MoviePage;

public interface Webservice {
    String API_KEY = "eea971c8e6615a1794951f8ff0ae6f2b";
    String POPULAR_MOVIE_URL = "http://api.themoviedb.org/3/movie/popular?api_key=";
    String HIGH_RATED_MOVIE_URL = "http://api.themoviedb.org/3/movie/popular?api_key=";

   // Call<MoviePage> getMoviePage();
}
