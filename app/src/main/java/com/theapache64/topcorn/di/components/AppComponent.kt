package com.Harsh Dhakate.MoviesMad.di.components

import com.Harsh Dhakate.MoviesMad.App
import com.Harsh Dhakate.MoviesMad.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    // inject the above given modules into this App class
    fun inject(app: App)
}