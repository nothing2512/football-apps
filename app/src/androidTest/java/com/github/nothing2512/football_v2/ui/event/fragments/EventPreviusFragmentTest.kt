package com.github.nothing2512.football_v2.ui.event.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.ui.league.LeagueActivity
import com.github.nothing2512.football_v2.utils.RecyclerViewAssertions
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.rule.CountingExecutorRule
import com.github.nothing2512.football_v2.utils.rule.IdlingRule
import com.github.nothing2512.football_v2.utils.rule.KoinRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventPreviusFragmentTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(LeagueActivity::class.java)

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
    fun loadPreviusEvent() {
        onView(withId(Id.btStatePrevius)).perform(click())
        onView(withId(Id.eventRecyclerView)).check(RecyclerViewAssertions(TestUtil.PREVIUS_EVENT_COUNT))
    }
}