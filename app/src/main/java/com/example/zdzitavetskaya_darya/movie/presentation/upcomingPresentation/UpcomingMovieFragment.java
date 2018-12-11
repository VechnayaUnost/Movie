package com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation;

import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.zdzitavetskaya_darya.movie.R;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;
import com.example.zdzitavetskaya_darya.movie.presentation.BaseFragment;
import com.example.zdzitavetskaya_darya.movie.presentation.MoviesAdapter;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.presenter.UpcomingPresenter;
import com.example.zdzitavetskaya_darya.movie.presentation.upcomingPresentation.presenter.UpcomingView;

import java.util.List;

import butterknife.BindView;

public class UpcomingMovieFragment extends BaseFragment implements UpcomingView {

    @InjectPresenter
    UpcomingPresenter upcomingPresenter;

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
