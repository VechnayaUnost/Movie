package com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zdzitavetskaya_darya.movie.App;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.model.UpcomingModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class UpcomingPresenter extends MvpPresenter<UpcomingView> implements MoviesModelCallback{

    private UpcomingModel upcomingModel;

    public UpcomingPresenter() {
        upcomingModel = new UpcomingModel(this);
    }

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
                        Log.i("AAA", "suc");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("AAA", "err");
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void onDestroy() {
        upcomingModel.onDestroyPresenter();
        super.onDestroy();
    }
}
