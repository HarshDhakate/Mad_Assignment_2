package com.Harsh Dhakate.MoviesMad.di.modules

import android.app.Application
import com.Harsh Dhakate.twinkill.di.modules.PreferenceModule

import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        ViewModelModule::class,
        PreferenceModule::class,
        ActivitiesBuilderModule::class,
        RepoModule::class
    ]
)
class AppModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return this.application
    }

}