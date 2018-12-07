package com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.model;

import com.example.zdzitavetskaya_darya.movie.App;
import com.example.zdzitavetskaya_darya.movie.extensions.Utility;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.BaseMVPModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.presenter.UpcomingPresenter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public final class UpcomingDatabaseModel extends BaseMVPModel{

    private final MoviesModelCallback callback;

    public UpcomingDatabaseModel(final UpcomingPresenter presenter) {
        this.callback = presenter;
        getMoviesFromDatabase();
    }

    public UpcomingDatabaseModel(final UpcomingPresenter presenter, List<MovieModel> movies) {
        this.callback = presenter;
        insertMoviesInDatabase(movies);
    }

    private void getMoviesFromDatabase() {
        App.getMovieDatabase().movieModelDao().getUpcomingMovies(true)
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

    private void insertMoviesInDatabase(List<MovieModel> movies) {
        Completable.fromAction(() -> App.getMovieDatabase().movieModelDao().insertAll(Utility.convertListToArray(movies)))
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}
