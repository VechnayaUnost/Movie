package com.example.zdzitavetskaya_darya.movie.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MovieModelDao {

    @Query("SELECT * FROM moviemodel WHERE isTrending = :isTrending")
    Single<List<MovieModel>> getTrendingMovies(boolean isTrending);

    @Query("SELECT * FROM moviemodel WHERE isUpcoming = :isUpcoming")
    Single<List<MovieModel>> getUpcomingMovies(boolean isUpcoming);

    @Query("SELECT * FROM moviemodel WHERE isFavourite = :isFavourite")
    Single<List<MovieModel>> getFavouriteMovies(boolean isFavourite);

    @Query("DELETE FROM moviemodel WHERE isTrending = :isTrending")
    void deleteAllTrending(boolean isTrending);

    @Insert
    void insert(MovieModel movie);

    @Delete
    void delete(MovieModel movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MovieModel... movieModels);
}
