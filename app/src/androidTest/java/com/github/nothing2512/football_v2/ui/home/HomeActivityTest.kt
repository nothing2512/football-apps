package com.github.nothing2512.football_v2.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.ui.event.fragments.SearchFragment
import com.github.nothing2512.football_v2.ui.league.LeagueFragment
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.SearchViewAction
import com.github.nothing2512.football_v2.utils.rule.CountingExecutorRule
import com.github.nothing2512.football_v2.utils.rule.IdlingRule
import com.github.nothing2512.football_v2.utils.rule.KoinRule
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

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
    fun testLeagueFragment() {
        onView(withId(R.id.btBackHome)).check(matches(not(isDisplayed())))
        assertTrue(koinRule.homeViewModel.fragment.value is LeagueFragment)
    }

    @Test
    fun testSearchFragment() {
        onView(withId(R.id.searchView)).perform(SearchViewAction("arsenal"))
        countingExecutorRule.drainTasks(
            Constants.SERVICE_LATENCY_IN_MILLIS.toInt(),
            TimeUnit.MILLISECONDS
        )
        onView(withId(R.id.btBackHome)).check(matches(isDisplayed()))
        assertTrue(koinRule.homeViewModel.fragment.value is SearchFragment)
    }
}