package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.model.MoviewModel;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.model.MoviewModelCallback;

import java.util.List;

@InjectViewState
public class MoviesPresenter extends MvpPresenter<MoviesView> implements MoviewModelCallback {


    private MoviewModel moviewModel;

    @Override
    public void onFilmsSuccess(List<MovieModel> movies) {
        Log.e("AAA", String.valueOf(movies.size()));
        getViewState().onFilmsSuccess(movies);
    }

    public MoviesPresenter() {
        moviewModel = new MoviewModel(this);
    }

    @Override
    public void onDestroy() {
        moviewModel.onDestroyPresenter();
        super.onDestroy();
    }
}
