package com.Harsh Dhakate.MoviesMad.utils.test

import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class IdlingRule : TestWatcher() {

    val dataBindingIdlingResource = DataBindingIdlingResource()

    override fun starting(description: Description?) {
        super.starting(description)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

}