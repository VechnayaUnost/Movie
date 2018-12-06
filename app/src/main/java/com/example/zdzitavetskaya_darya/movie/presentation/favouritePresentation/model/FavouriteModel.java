package com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.model;

import com.example.zdzitavetskaya_darya.movie.App;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.BaseMVPModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.presenter.FavouritePresenter;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public final class FavouriteModel extends BaseMVPModel {

    private final MoviesModelCallback callback;

    public FavouriteModel(final FavouritePresenter presenter) {
        this.callback = presenter;
        getMoviesFromDatabase();
    }

    private void getMoviesFromDatabase() {
        App.getMovieDatabase().movieModelDao().getFavouriteMovies(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<MovieModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<MovieModel> movieModels) {
                        callback.onFilmsSuccess(movieModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}
