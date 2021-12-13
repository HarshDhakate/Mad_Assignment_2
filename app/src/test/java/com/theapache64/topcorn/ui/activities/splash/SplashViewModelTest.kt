package com.Harsh Dhakate.MoviesMad.ui.activities.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.Harsh Dhakate.MoviesMad.utils.test.MainCoroutineRule
import com.Harsh Dhakate.MoviesMad.utils.test.getOrAwaitValue
import com.Harsh Dhakate.MoviesMad.ui.activities.feed.FeedActivity
import com.winterbe.expekt.should
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalCoroutinesApi
class SplashViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutineRule()

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun init() {
        splashViewModel = SplashViewModel()
    }

    @Test
    fun `After splash, should go to feed`() {
        // when : Calling subject -> navigation
        val actualResult = splashViewModel.launchActivityEvent.getOrAwaitValue {
            coroutinesRule.advanceTimeBy(SplashViewModel.SPLASH_DURATION)
        }
        actualResult.should.equal(FeedActivity::class.simpleName)
    }

}