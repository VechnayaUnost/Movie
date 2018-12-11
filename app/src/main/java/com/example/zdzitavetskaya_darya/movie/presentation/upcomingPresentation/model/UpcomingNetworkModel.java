package com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.model;

import com.example.zdzitavetskaya_darya.movie.App;
import com.example.zdzitavetskaya_darya.movie.constants.Constants;
import com.example.zdzitavetskaya_darya.movie.model.MovieCover;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.BaseMVPModel;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesModelCallback;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.presenter.UpcomingPresenter;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public final class UpcomingNetworkModel extends BaseMVPModel{

    private final MoviesModelCallback callback;

    public UpcomingNetworkModel(final UpcomingPresenter presenter) {
        this.callback = presenter;
        getFilmsFromNetwork();
    }

    private void getFilmsFromNetwork() {
        App.getMovieApi().getUpcoming(Constants.API_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieCover>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(final MovieCover movieCover) {
                        for (final MovieModel movie: movieCover.getMovies()) {
                            movie.setUpcoming(true);
                        }
                        callback.onFilmsSuccess(movieCover.getMovies());
                    }

                    @Override
                    public void onError(final Throwable e) {
                        callback.onFilmsError();
                        e.printStackTrace();
                    }
                });
    }
}
