package com.example.zdzitavetskaya_darya.movie.api;

import com.example.zdzitavetskaya_darya.movie.model.MovieCover;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("trending/all/week")
    Observable<MovieCover> getTrends(@Query("api_key") String apiKey);
}
