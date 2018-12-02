package com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.model.UpcomingModel;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.model.UpcomingModelCallback;

import java.util.List;

@InjectViewState
public class UpcomingPresenter extends MvpPresenter<UpcomingView> implements UpcomingModelCallback{

    private UpcomingModel upcomingModel;

    public UpcomingPresenter() {
        upcomingModel = new UpcomingModel(this);
    }

    @Override
    public void onFilmsSuccess(List<MovieModel> movies) {
        getViewState().onFilmsSuccess(movies);
    }

    @Override
    public void onDestroy() {
        upcomingModel.onDestroyPresenter();
        super.onDestroy();
    }
}
