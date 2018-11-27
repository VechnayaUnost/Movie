package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter;

import com.arellomobile.mvp.MvpView;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.util.List;

public interface MoviesView extends MvpView {

    void onFilmsSuccess(List<MovieModel> movies);
}
