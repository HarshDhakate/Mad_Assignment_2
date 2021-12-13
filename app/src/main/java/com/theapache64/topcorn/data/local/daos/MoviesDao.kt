package com.Harsh Dhakate.MoviesMad.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.Harsh Dhakate.MoviesMad.data.remote.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("DELETE FROM movies")
    fun deleteAll()

    @Insert
    fun addAll(data: List<Movie>)
}
