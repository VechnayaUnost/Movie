package com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.model.UpcomingDatabaseModel;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.model.UpcomingNetworkModel;

import java.util.List;

@InjectViewState
public class UpcomingPresenter extends MvpPresenter<UpcomingView> implements MoviesModelCallback{

    private UpcomingDatabaseModel upcomingDatabaseModel;
    private UpcomingNetworkModel upcomingNetworkModel;

    public UpcomingPresenter() {
        upcomingNetworkModel = new UpcomingNetworkModel(this);
        upcomingDatabaseModel = new UpcomingDatabaseModel(this);
    }

    @Override
    public void onFilmsSuccess(final List<MovieModel> movies) {
        getViewState().onFilmsSuccess(movies);
        upcomingDatabaseModel.insertMoviesInDatabase(movies);
    }

    @Override
    public void onFilmsError() {
        upcomingDatabaseModel.getMoviesFromDatabase();
    }

    @Override
    public void onDestroy() {
        upcomingNetworkModel.onDestroyPresenter();
        upcomingDatabaseModel.onDestroyPresenter();
        super.onDestroy();
    }
}
