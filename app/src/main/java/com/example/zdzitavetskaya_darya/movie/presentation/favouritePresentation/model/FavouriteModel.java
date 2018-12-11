package com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.model;

import com.example.zdzitavetskaya_darya.movie.App;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.BaseMVPModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.presenter.FavouritePresenter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
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
                    public void onSubscribe(final Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(final List<MovieModel> movieModels) {
                        callback.onFilmsSuccess(movieModels);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    private void insertMovieInDatabase(final MovieModel movie) {
        Completable.fromAction(() -> App.getMovieDatabase().movieModelDao().insert(movie))
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(final Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(final Throwable e) {

                    }
                });
    }
}
