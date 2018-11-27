package com.example.zdzitavetskaya_darya.movie;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<MovieModel> movies;

    private static final String BASE_POSTER_URL = "https://image.tmdb.org/t/p/original/";

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
                .load(BASE_POSTER_URL + posterPath)
                .into(holder.moviePoster);

        holder.releaseDate.setText(getFormatDate(movie.getReleaseDate()));

        holder.overview.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        if (movies == null)
            return 0;
        return movies.size();
    }

    private String getFormatDate(String date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            try {
                Date newDate = dateFormat.parse(date);
                return DateFormat.format("d MMMM y", newDate).toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
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
