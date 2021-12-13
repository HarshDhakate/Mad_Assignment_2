package com.Harsh Dhakate.MoviesMad.di.modules

import android.content.SharedPreferences
import com.Harsh Dhakate.MoviesMad.data.local.daos.MoviesDao
import com.Harsh Dhakate.MoviesMad.data.remote.ApiInterface
import com.Harsh Dhakate.MoviesMad.data.repositories.movies.MoviesRepo
import com.Harsh Dhakate.MoviesMad.utils.test.OpenForTesting
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@OpenForTesting
@Module
class RepoModule {

    @Singleton
    @Provides
    fun provideMoviesRepo(
        sharedPref: SharedPreferences,
        apiInterface: ApiInterface,
        moviesDao: MoviesDao
    ): MoviesRepo {
        return MoviesRepo(sharedPref, apiInterface, moviesDao)
    }

}