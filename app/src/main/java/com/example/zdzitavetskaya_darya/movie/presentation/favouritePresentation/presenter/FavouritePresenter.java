package com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zdzitavetskaya_darya.movie.App;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.model.FavouriteModel;
import com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.model.FavouriteModelCallback;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class FavouritePresenter extends MvpPresenter<FavouriteView> implements MoviesModelCallback, FavouriteModelCallback {

    private FavouriteModel favouriteModel;

    public FavouritePresenter() {
        favouriteModel = new FavouriteModel(this);
    }

    @Override
    public void onFilmsSuccess(List<MovieModel> movies) {
        getViewState().onFilmsSuccess(movies);
    }

    @Override
    public void onFilmSuccess(MovieModel movie) {
        getViewState().onFilmSuccess(movie);

        Completable.fromAction(() -> App.getMovieDatabase().movieModelDao().insert(movie))
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

                    }
                });
    }

    @Override
    public void onDestroy() {
        favouriteModel.onDestroyPresenter();
        super.onDestroy();
    }
}
