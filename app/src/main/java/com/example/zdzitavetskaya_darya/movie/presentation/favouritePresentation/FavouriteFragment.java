package com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.zdzitavetskaya_darya.movie.R;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.BaseFragment;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesAdapter;
import com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.presenter.FavouritePresenter;
import com.example.zdzitavetskaya_darya.movie.presentation.favouritePresentation.presenter.FavouriteView;

import java.util.List;

import butterknife.BindView;

public class FavouriteFragment extends BaseFragment implements FavouriteView{

    @InjectPresenter
    FavouritePresenter favouritePresenter;

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;

    @Override
    public RecyclerView getRecycler() {
        return recyclerView;
    }

    @Override
    public int getLayoutFragment() {
        return R.layout.fragment_movie;
    }

    @Override
    public void onFilmsSuccess(final List<MovieModel> movies) {
        ((MoviesAdapter) recyclerView.getAdapter()).getMovies().addAll(movies);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onFilmSuccess(final MovieModel movie) {

    }
}
