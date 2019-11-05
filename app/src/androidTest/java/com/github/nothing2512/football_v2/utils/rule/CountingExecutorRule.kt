package com.github.nothing2512.football_v2.utils.rule

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.github.nothing2512.football_v2.utils.resources.Constants
import org.junit.runner.Description
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit

class CountingExecutorRule : CountingTaskExecutorRule() {

    private val id = UUID.randomUUID().toString()

    private val callbacks = CopyOnWriteArrayList<IdlingResource.ResourceCallback>()

    private val idlingResources = object: IdlingResource {
        override fun getName(): String = "resource $id"

        override fun isIdleNow(): Boolean = isIdle

        override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
            callbacks.add(callback)
        }
    }

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResources)
        super.starting(description)
    }

    override fun finished(description: Description?) {
        drainTasks(Constants.SERVICE_LATENCY_IN_MILLIS.toInt(), TimeUnit.MILLISECONDS)
        callbacks.clear()
        IdlingRegistry.getInstance().unregister(idlingResources)
        super.finished(description)
    }

    override fun onIdle() {
        super.onIdle()
        for ( item in callbacks ) item.onTransitionToIdle()
    }
}