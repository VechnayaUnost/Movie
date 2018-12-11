package com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.model.FavouriteModel;
import com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.model.FavouriteModelCallback;

import java.util.List;

@InjectViewState
public class FavouritePresenter extends MvpPresenter<FavouriteView> implements MoviesModelCallback, FavouriteModelCallback {

    private final FavouriteModel favouriteModel;

    public FavouritePresenter() {
        favouriteModel = new FavouriteModel(this);
    }

    @Override
    public void onFilmsSuccess(final List<MovieModel> movies) {
        getViewState().onFilmsSuccess(movies);
    }

    @Override
    public void onFilmsError() {

    }

    @Override
    public void onFilmSuccess(final MovieModel movie) {
        getViewState().onFilmSuccess(movie);
        //insert movie in DB
    }

    @Override
    public void onDestroy() {
        favouriteModel.onDestroyPresenter();
        super.onDestroy();
    }
}
