package com.Harsh Dhakate.MoviesMad.data.repositories.movies

import android.content.SharedPreferences
import androidx.core.content.edit
import com.Harsh Dhakate.MoviesMad.data.local.daos.MoviesDao
import com.Harsh Dhakate.MoviesMad.data.remote.ApiInterface
import com.Harsh Dhakate.MoviesMad.data.remote.Movie
import com.Harsh Dhakate.MoviesMad.utils.NetworkBoundResource
import com.Harsh Dhakate.MoviesMad.utils.test.OpenForTesting
import com.Harsh Dhakate.twinkill.network.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.time.ExperimentalTime
import kotlin.time.hours

@OpenForTesting
class MoviesRepo @Inject constructor(
    private val sharedPref: SharedPreferences,
    private val apiInterface: ApiInterface,
    private val moviesDao: MoviesDao
) {

    companion object {
        @ExperimentalTime
        private val MOVIE_EXPIRY_IN_MILLIS = 1.hours.inMilliseconds.toLong()
        private const val KEY_LAST_SYNCED = "last_synced"
    }

    @ExperimentalCoroutinesApi
    @ExperimentalTime
    fun getTop250Movies(): Flow<Resource<List<Movie>>> {

        return object : NetworkBoundResource<List<Movie>, List<Movie>>() {

            override fun fetchFromLocal(): Flow<List<Movie>> {
                return moviesDao.getAllMovies()
            }

            override fun fetchFromRemote(): Flow<Resource<List<Movie>>> {
                return apiInterface.getTop250Movies()
            }

            override fun saveRemoteData(data: List<Movie>) {
                moviesDao.deleteAll()
                moviesDao.addAll(data)
                sharedPref.edit {
                    putLong(KEY_LAST_SYNCED, System.currentTimeMillis())
                }
            }

            override fun shouldFetchFromRemote(data: List<Movie>): Boolean {
                val lastSynced = sharedPref.getLong(KEY_LAST_SYNCED, -1)
                return lastSynced == -1L ||
                        data.isNullOrEmpty() ||
                        isExpired(lastSynced)
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }

    @ExperimentalTime
    private fun isExpired(lastSynced: Long): Boolean {
        val currentTime = System.currentTimeMillis()
        return (currentTime - lastSynced) >= MOVIE_EXPIRY_IN_MILLIS
    }
}