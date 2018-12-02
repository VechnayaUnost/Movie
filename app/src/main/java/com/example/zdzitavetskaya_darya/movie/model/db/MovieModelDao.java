package com.example.zdzitavetskaya_darya.movie.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.zdzitavetskaya_darya.movie.model.MovieModel;

import java.util.List;

@Dao
public interface MovieModelDao {

    @Query("SELECT * FROM moviemodel")
    List<MovieModel> getFavourite();

    @Insert
    void insert(MovieModel movie);

    @Delete
    void delete(MovieModel movie);

    @Insert
    void insertAll(MovieModel... movie);
}
