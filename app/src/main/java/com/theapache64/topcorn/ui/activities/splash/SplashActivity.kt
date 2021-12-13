package com.Harsh Dhakate.MoviesMad.ui.activities.splash


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.Harsh Dhakate.MoviesMad.R
import com.Harsh Dhakate.MoviesMad.databinding.ActivitySplashBinding
import com.Harsh Dhakate.MoviesMad.ui.activities.feed.FeedActivity
import com.Harsh Dhakate.twinkill.ui.activities.base.BaseAppCompatActivity
import com.Harsh Dhakate.twinkill.utils.extensions.bindContentView
import dagger.android.AndroidInjection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class SplashActivity : BaseAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding = bindContentView<ActivitySplashBinding>(R.layout.activity_splash)
        val viewModel = ViewModelProvider(this, factory).get(SplashViewModel::class.java)
        binding.viewModel = viewModel

        // Watching activity launch command
        viewModel.launchActivityEvent.observe(this, Observer { activityName ->

            when (activityName) {

                FeedActivity::class.simpleName -> {
                    startActivity(FeedActivity.getStartIntent(this))
                }

                else -> throw IllegalArgumentException("Undefined activity id $activityName")
            }

            finish()
        })
    }

}
