package com.example.zdzitavetskaya_darya.movie;

import android.app.Application;

import com.example.zdzitavetskaya_darya.movie.api.MovieApi;
import com.example.zdzitavetskaya_darya.movie.api.RetrofitClient;
import com.example.zdzitavetskaya_darya.movie.model.db.MovieDatabase;

public class App extends Application {
    private static MovieApi movieApi;
    private static MovieDatabase movieDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        movieDatabase = MovieDatabase.getDatabase(getApplicationContext());

        movieApi = RetrofitClient.getRetrofitInstance().create(MovieApi.class);
    }

    public static MovieApi getMovieApi() {
        return movieApi;
    }

    public static MovieDatabase getMovieDatabase() {
        return movieDatabase;
    }
}
