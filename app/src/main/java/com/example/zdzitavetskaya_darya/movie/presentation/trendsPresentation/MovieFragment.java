package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.zdzitavetskaya_darya.movie.R;
import com.example.zdzitavetskaya_darya.movie.api.MovieApi;
import com.example.zdzitavetskaya_darya.movie.api.RetrofitClient;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter.MoviesPresenter;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter.MoviesView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieFragment extends MvpAppCompatFragment implements MoviesView {

    @InjectPresenter
    MoviesPresenter moviesPresenter;
    //private static Retrofit retrofit = null;

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;

    List<MovieModel> movies;

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


        movies = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        MoviesAdapter adapter = new MoviesAdapter(movies);
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onFilmsSuccess(List<MovieModel> movies) {
        this.movies.addAll(movies);
        Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
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
