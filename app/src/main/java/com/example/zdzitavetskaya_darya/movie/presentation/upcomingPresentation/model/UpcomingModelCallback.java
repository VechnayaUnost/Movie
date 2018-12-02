package com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.model;

import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.util.List;

public interface UpcomingModelCallback {

    void onFilmsSuccess(List<MovieModel> movies);
}
