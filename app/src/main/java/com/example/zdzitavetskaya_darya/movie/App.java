package com.example.zdzitavetskaya_darya.movie;

import android.app.Application;

import com.example.zdzitavetskaya_darya.movie.api.MovieApi;
import com.example.zdzitavetskaya_darya.movie.api.RetrofitClient;

public class App extends Application {
    private static MovieApi movieApi;

    @Override
    public void onCreate() {
        super.onCreate();

        movieApi = RetrofitClient.getRetrofitInstance().create(MovieApi.class);
    }

    public static MovieApi getMovieApi() {
        return movieApi;
    }
}
