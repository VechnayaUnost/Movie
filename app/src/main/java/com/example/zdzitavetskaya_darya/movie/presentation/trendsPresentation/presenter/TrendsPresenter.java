package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.model.TrendsModel;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.model.TrendsModelCallback;

import java.util.List;

@InjectViewState
public class TrendsPresenter extends MvpPresenter<TrendsView> implements TrendsModelCallback {

    private TrendsModel mTrendsModel;

    @Override
    public void onFilmsSuccess(List<MovieModel> movies) {
        getViewState().onFilmsSuccess(movies);
    }

    public TrendsPresenter() {
        mTrendsModel = new TrendsModel(this);
    }

    @Override
    public void onDestroy() {
        mTrendsModel.onDestroyPresenter();
        super.onDestroy();
    }
}
