package com.example.zdzitavetskaya_darya.movie.presentation;

import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.util.List;

public interface MoviesModelCallback {

    void onFilmsSuccess(List<MovieModel> movies);

    void onFilmsError();
}
