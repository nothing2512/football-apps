package com.github.nothing2512.football_v2.ui.event.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.ui.home.HomeActivity
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.RecyclerViewAssertions
import com.github.nothing2512.football_v2.utils.SearchViewAction
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

        onView(withId(R.id.searchView)).perform(SearchViewAction("arsenal"))
        countingExecutorRule.drainTasks(
            Constants.SERVICE_LATENCY_IN_MILLIS.toInt(),
            TimeUnit.MILLISECONDS
        )
        onView(withId(R.id.btBackHome)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.eventRecyclerView)).check(RecyclerViewAssertions(TestUtil.ARSENAL_COUNT))
    }

    @Test
    fun searchBarcelona() {

        onView(withId(R.id.searchView)).perform(SearchViewAction("barcelona"))
        countingExecutorRule.drainTasks(
            Constants.SERVICE_LATENCY_IN_MILLIS.toInt(),
            TimeUnit.MILLISECONDS
        )
        onView(withId(R.id.btBackHome)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.eventRecyclerView)).check(RecyclerViewAssertions(TestUtil.BARCELONA_COUNT))
    }

    @Test
    fun searchNotFound() {

        onView(withId(R.id.searchView)).perform(SearchViewAction("not found"))
        countingExecutorRule.drainTasks(
            Constants.SERVICE_LATENCY_IN_MILLIS.toInt(),
            TimeUnit.MILLISECONDS
        )
        onView(withId(R.id.btBackHome)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.eventRecyclerView)).check(RecyclerViewAssertions(1))
        onView(withId(R.id.tvNotFound)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}