package com.Harsh Dhakate.MoviesMad.models

import com.Harsh Dhakate.MoviesMad.data.remote.Movie


data class FeedItem(
    val id: Long,
    val genre: String,
    val movies: List<Movie>
)
