package com.github.nothing2512.football_v2.ui.event.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.ui.home.HomeActivity
import com.github.nothing2512.football_v2.utils.RecyclerViewAssertions
import com.github.nothing2512.football_v2.utils.SearchViewAction
import com.github.nothing2512.football_v2.utils.resources.Constants
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.rule.CountingExecutorRule
import com.github.nothing2512.football_v2.utils.rule.IdlingRule
import com.github.nothing2512.football_v2.utils.rule.KoinRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Rule
    @JvmField
    val idlingRule = IdlingRule(activityTestRule)

    @Rule
    @JvmField
    val countingExecutorRule = CountingExecutorRule()

    @Rule
    @JvmField
    val koinRule = KoinRule()

    @Test
    fun searchArsenal() {

        onView(withId(Id.searchView)).perform(SearchViewAction("arsenal"))
        countingExecutorRule.drainTasks(
            Constants.SERVICE_LATENCY_IN_MILLIS.toInt(),
            TimeUnit.MILLISECONDS
        )
        onView(withId(Id.btBackHome)).check(matches(isDisplayed()))
        onView(withId(Id.eventRecyclerView)).check(RecyclerViewAssertions(TestUtil.ARSENAL_COUNT))
    }

    @Test
    fun searchBarcelona() {

        onView(withId(Id.searchView)).perform(SearchViewAction("barcelona"))
        countingExecutorRule.drainTasks(
            Constants.SERVICE_LATENCY_IN_MILLIS.toInt(),
            TimeUnit.MILLISECONDS
        )
        onView(withId(Id.btBackHome)).check(matches(isDisplayed()))
        onView(withId(Id.eventRecyclerView)).check(RecyclerViewAssertions(TestUtil.BARCELONA_COUNT))
    }

    @Test
    fun searchNotFound() {
        
        onView(withId(Id.searchView)).perform(SearchViewAction("not found"))
        countingExecutorRule.drainTasks(
            Constants.SERVICE_LATENCY_IN_MILLIS.toInt(),
            TimeUnit.MILLISECONDS
        )
        onView(withId(Id.btBackHome)).check(matches(isDisplayed()))
        onView(withId(Id.eventRecyclerView)).check(RecyclerViewAssertions(1))
        onView(withId(Id.tvNotFound)).check(matches(isDisplayed()))
    }
}