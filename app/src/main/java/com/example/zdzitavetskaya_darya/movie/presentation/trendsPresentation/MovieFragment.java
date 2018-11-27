package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zdzitavetskaya_darya.movie.R;
import com.example.zdzitavetskaya_darya.movie.api.MovieApi;
import com.example.zdzitavetskaya_darya.movie.api.RetrofitClient;
import com.example.zdzitavetskaya_darya.movie.constants.Constants;
import com.example.zdzitavetskaya_darya.movie.model.MovieCover;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment {

    //private static Retrofit retrofit = null;

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;

    List<MovieModel> movies;

    private static MovieApi movieApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, viewGroup, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movieApi = RetrofitClient.getRetrofitInstance().create(MovieApi.class);

        movies = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        MoviesAdapter adapter = new MoviesAdapter(movies);
        recyclerView.setAdapter(adapter);

        Observable<MovieCover> observable = getMovieApi().getTrends(Constants.API_KEY);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieCover>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieCover movieCover) {
                        movies.addAll(movieCover.getMovies());
                        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
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

    public static MovieApi getMovieApi() {
        return movieApi;
    }

    /*public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build();
        }
        return retrofit;
    }*/
}
