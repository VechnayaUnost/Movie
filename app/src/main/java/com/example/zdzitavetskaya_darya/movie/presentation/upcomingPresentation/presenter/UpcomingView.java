package com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.presenter;

import com.arellomobile.mvp.MvpView;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.util.List;

public interface UpcomingView extends MvpView{

    void onFilmsSuccess(List<MovieModel> movies);
}
