package com.example.zdzitavetskaya_darya.movie.extensions;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class Utility {

    public static String getFormatDate(final String date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            try {
                final Date newDate = dateFormat.parse(date);
                return DateFormat.format("d MMMM y", newDate).toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
    }

    public static MovieModel[] convertListToArray(final List<MovieModel> movies) {
        MovieModel[] movieArray = new MovieModel[movies.size()];
        return movies.toArray(movieArray);
    }
}
