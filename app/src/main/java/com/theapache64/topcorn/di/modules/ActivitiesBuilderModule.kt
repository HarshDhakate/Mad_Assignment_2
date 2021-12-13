package com.Harsh Dhakate.MoviesMad.di.modules


import com.Harsh Dhakate.MoviesMad.ui.activities.feed.FeedActivity
import com.Harsh Dhakate.MoviesMad.ui.activities.movie.MovieActivity
import com.Harsh Dhakate.MoviesMad.ui.activities.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * To hold activities to support AndroidInjection call from dagger.
 */
@Module
abstract class ActivitiesBuilderModule {

    @ContributesAndroidInjector
    abstract fun getSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun getMainActivity(): FeedActivity

    @ContributesAndroidInjector
    abstract fun getMovieActivity(): MovieActivity

}