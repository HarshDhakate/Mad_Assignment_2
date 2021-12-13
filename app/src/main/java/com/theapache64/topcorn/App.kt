package com.Harsh Dhakate.MoviesMad


import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.Harsh Dhakate.MoviesMad.di.components.DaggerAppComponent
import com.Harsh Dhakate.MoviesMad.utils.retrofit.FlowResourceCallAdapterFactory
import com.Harsh Dhakate.twinkill.TwinKill
import com.Harsh Dhakate.twinkill.di.modules.ContextModule
import com.Harsh Dhakate.twinkill.googlesans.GoogleSans
import com.Harsh Dhakate.twinkill.network.di.modules.BaseNetworkModule
import com.Harsh Dhakate.twinkill.network.utils.retrofit.interceptors.CurlInterceptor
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/Harsh Dhakate/top250/master/"
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Dagger
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .baseNetworkModule(BaseNetworkModule(BASE_URL))
            .build().inject(this)

        // TwinKill
        TwinKill.init(
            TwinKill
                .builder()
                .addCallAdapter(FlowResourceCallAdapterFactory()) // to return Flow<Resource<T>> from retrofit
                .addOkHttpInterceptor(CurlInterceptor())
                .setDefaultFont(GoogleSans.Regular)
                .build()
        )

    }

}
