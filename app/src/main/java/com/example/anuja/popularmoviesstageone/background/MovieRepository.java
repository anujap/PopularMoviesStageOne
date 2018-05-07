package com.example.anuja.popularmoviesstageone.background;

import com.example.anuja.popularmoviesstageone.model.MoviePage;
import com.example.anuja.popularmoviesstageone.model.MovieDetails;
import com.example.anuja.popularmoviesstageone.webservice.MovieWebserviceClient;
import com.example.anuja.popularmoviesstageone.webservice.MovieWebserviceInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class used to download and parse the API data.
 */
public class MovieRepository {

    private MovieWebserviceInterface movieWebservice = MovieWebserviceClient.getInstance().getMovieWebservice();

    ArrayList<MovieDetails> movies;

    /**
     * Function used to download the movies specific to the endpoints
     * (popular/top_rated)
     * @param endpoint - popular/top_rated
     * @param apiKey - api key
     * @return returns the list of movies specific to the criteria
     */
    public ArrayList<MovieDetails> getMovies(String endpoint, String apiKey) {
        movies = new ArrayList<>();

        movieWebservice.getMovies(endpoint, apiKey).enqueue(new Callback<MoviePage>() {
            @Override
            public void onResponse(Call<MoviePage> call, Response<MoviePage> response) {
                movies = response.body().getResults();
            }

            @Override
            public void onFailure(Call<MoviePage> call, Throwable t) {

            }
        });
        return movies;
    }
}
