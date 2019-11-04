package com.github.nothing2512.football_v2.utils.rule

import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import com.github.nothing2512.football_v2.utils.EspressoIdlingResource
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class IdlingRule (activityTestRule: ActivityTestRule<*>): TestWatcher() {

    private val resource =
        DataBindingIdlingResources(activityTestRule)
    private val count = EspressoIdlingResource.count

    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(resource)
        IdlingRegistry.getInstance().unregister(count)
        super.finished(description)
    }

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(resource)
        IdlingRegistry.getInstance().register(count)
        super.starting(description)
    }
}