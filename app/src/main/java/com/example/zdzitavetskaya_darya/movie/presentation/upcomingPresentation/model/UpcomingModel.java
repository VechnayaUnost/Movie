package com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.model;

import com.example.zdzitavetskaya_darya.movie.App;
import com.example.zdzitavetskaya_darya.movie.constants.Constants;
import com.example.zdzitavetskaya_darya.movie.model.MovieCover;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.BaseMVPModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.presenter.UpcomingPresenter;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public final class UpcomingModel extends BaseMVPModel{

    private final MoviesModelCallback callback;

    public UpcomingModel(final UpcomingPresenter presenter) {
        this.callback = presenter;
        getUpcomingMovies();
    }

    private void getUpcomingMovies() {
        App.getMovieApi().getUpcoming(Constants.API_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieCover>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(MovieCover movieCover) {
                        for (MovieModel movie: movieCover.getMovies()) {
                            movie.setUpcoming(true);
                        }
                        callback.onFilmsSuccess(movieCover.getMovies());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMoviesFromDatabase();
                        e.printStackTrace();
                    }
                });
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
}
