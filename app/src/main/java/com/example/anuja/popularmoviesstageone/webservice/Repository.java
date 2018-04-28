package com.example.anuja.popularmoviesstageone.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private MovieService movieService;

    private static Repository repository;

    public synchronized static Repository getInstance() {
        if(repository == null) {
            repository = new Repository();
        }

        return repository;
    }

    private Repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieUtils.MOVIE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieService = retrofit.create(MovieService.class);

    }
}
