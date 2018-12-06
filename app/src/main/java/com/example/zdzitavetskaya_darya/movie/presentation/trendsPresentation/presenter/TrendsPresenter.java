package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zdzitavetskaya_darya.movie.App;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.model.TrendsModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class TrendsPresenter extends MvpPresenter<TrendsView> implements MoviesModelCallback {

    private TrendsModel mTrendsModel;

    @Override
    public void onFilmsSuccess(List<MovieModel> movies) {
        getViewState().onFilmsSuccess(movies);

        Completable.fromAction(() -> App.getMovieDatabase().movieModelDao().insertAll(movies))
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("BB", "suc");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("BB", "err");
                        e.printStackTrace();
                    }
                });
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
