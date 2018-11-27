package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.model;

import android.util.Log;

import com.example.zdzitavetskaya_darya.movie.api.MovieApi;
import com.example.zdzitavetskaya_darya.movie.api.RetrofitClient;
import com.example.zdzitavetskaya_darya.movie.constants.Constants;
import com.example.zdzitavetskaya_darya.movie.model.MovieCover;
import com.example.zdzitavetskaya_darya.movie.presentation.BaseMVPModel;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter.MoviesPresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviewModel extends BaseMVPModel {

    MoviewModelCallback callback;

    public MoviewModel(MoviesPresenter presenter) {
        this.callback = presenter;
        getFilmsFromNetwork();
        Log.e("AA","TEST");
    }

    void getFilmsFromNetwork() {
        MovieApi movieApi = RetrofitClient.getRetrofitInstance().create(MovieApi.class);

        movieApi.getTrends(Constants.API_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieCover>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MovieCover movieCover) {
                        callback.onFilmsSuccess(movieCover.getMovies());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
