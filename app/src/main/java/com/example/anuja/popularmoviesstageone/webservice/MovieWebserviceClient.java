package com.example.anuja.popularmoviesstageone.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class MovieWebserviceClient {

    private static MovieWebserviceInterface movieWebservice;

    private static MovieWebserviceClient mwClient;

    public synchronized static MovieWebserviceClient getInstance() {
        if(mwClient == null) {
            mwClient = new MovieWebserviceClient();
        }

        return mwClient;
    }

    private MovieWebserviceClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MovieUtils.MOVIE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieWebservice = retrofit.create(MovieWebserviceInterface.class);

    }

    public MovieWebserviceInterface getMovieWebservice() {
        return movieWebservice;
    }
}
