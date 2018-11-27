package com.example.zdzitavetskaya_darya.movie.presentation.trendsPresentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zdzitavetskaya_darya.movie.module.GlideApp;
import com.example.zdzitavetskaya_darya.movie.R;
import com.example.zdzitavetskaya_darya.movie.constants.Constants;
import com.example.zdzitavetskaya_darya.movie.extensions.Utility;
import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<MovieModel> movies;

    MoviesAdapter(List<MovieModel> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModel movie = movies.get(position);

        holder.title.setText(movie.getTitle());

        String posterPath = movie.getPosterPath();
        GlideApp.with(holder.moviePoster.getContext())
                .load(Constants.BASE_POSTER_URL + posterPath)
                .into(holder.moviePoster);

        holder.releaseDate.setText(Utility.getFormatDate(movie.getReleaseDate()));

        holder.overview.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        if (movies == null)
            return 0;
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_title)
        TextView title;

        @BindView(R.id.movie_poster)
        ImageView moviePoster;

        @BindView(R.id.movie_overview)
        TextView overview;

        @BindView(R.id.movie_release_date)
        TextView releaseDate;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
