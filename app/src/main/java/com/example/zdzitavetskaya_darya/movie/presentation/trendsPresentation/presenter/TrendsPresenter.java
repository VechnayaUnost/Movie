package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.model.TrendsDatabaseModel;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.model.TrendsNetworksModel;

import java.util.List;

@InjectViewState
public class TrendsPresenter extends MvpPresenter<TrendsView> implements MoviesModelCallback {

    private final TrendsNetworksModel trendsNetworksModel;
    private final TrendsDatabaseModel trendsDatabaseModel;

    public TrendsPresenter() {
        trendsNetworksModel = new TrendsNetworksModel(this);
        trendsDatabaseModel = new TrendsDatabaseModel(this);
    }

    @Override
    public void onFilmsSuccess(final List<MovieModel> movies) {
        getViewState().onFilmsSuccess(movies);
        trendsDatabaseModel.insertMoviesInDatabase(movies);
    }

    @Override
    public void onFilmsError() {
        trendsDatabaseModel.getMoviesFromDatabase();
    }

    @Override
    public void onDestroy() {
        trendsNetworksModel.onDestroyPresenter();
        trendsDatabaseModel.onDestroyPresenter();
        super.onDestroy();
    }
}
