package com.example.zdzitavetskaya_darya.movie.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

@Database(entities = {MovieModel.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieModelDao movieModelDao();

    private static volatile MovieDatabase INSTANCE;

    public static MovieDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase.class, "movie_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
