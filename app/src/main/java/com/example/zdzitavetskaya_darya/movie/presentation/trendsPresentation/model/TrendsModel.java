package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.model;

import com.example.zdzitavetskaya_darya.movie.App;
import com.example.zdzitavetskaya_darya.movie.constants.Constants;
import com.example.zdzitavetskaya_darya.movie.model.MovieCover;
import com.example.zdzitavetskaya_darya.movie.presentation.BaseMVPModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter.TrendsPresenter;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrendsModel extends BaseMVPModel {

    private MoviesModelCallback callback;

    public TrendsModel(TrendsPresenter presenter) {
        this.callback = presenter;
        getFilmsFromNetwork();
    }

    private void getFilmsFromNetwork() {
        App.getMovieApi().getTrends(Constants.API_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieCover>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(MovieCover movieCover) {
                        callback.onFilmsSuccess(movieCover.getMovies());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}
