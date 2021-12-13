package com.Harsh Dhakate.MoviesMad.di.modules

import android.content.Context
import androidx.room.Room
import com.Harsh Dhakate.MoviesMad.data.local.AppDatabase
import com.Harsh Dhakate.twinkill.di.modules.ContextModule
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "com.Harsh Dhakate.MoviesMad_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase) = appDatabase.movieDao()
}