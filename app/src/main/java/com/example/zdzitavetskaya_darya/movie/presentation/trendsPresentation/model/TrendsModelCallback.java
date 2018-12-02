package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.model;

import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.util.List;

public interface TrendsModelCallback {

    void onFilmsSuccess(List<MovieModel> movies);
}
