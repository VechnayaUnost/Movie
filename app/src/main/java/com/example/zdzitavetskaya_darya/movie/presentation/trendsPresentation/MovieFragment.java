package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.zdzitavetskaya_darya.movie.R;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.BaseFragment;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesAdapter;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter.TrendsPresenter;
import com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation.presenter.TrendsView;

import java.util.List;

import butterknife.BindView;

public class MovieFragment extends BaseFragment implements TrendsView {

    @InjectPresenter
    TrendsPresenter mTrendsPresenter;

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onFilmsSuccess(final List<MovieModel> movies) {
        ((MoviesAdapter) recyclerView.getAdapter()).getMovies().addAll(movies);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public RecyclerView getRecycler() {
        return recyclerView;
    }

    @Override
    public int getLayoutFragment() {
        return R.layout.fragment_movie;
    }
}
