package com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.presenter;

import com.arellomobile.mvp.MvpView;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.util.List;

public interface FavouriteView extends MvpView{

    void onFilmsSuccess(List<MovieModel> movies);

    void onFilmSuccess(MovieModel movie);
}
