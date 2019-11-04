package com.github.nothing2512.football_v2.ui.loved.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.ui.loved.LovedActivity
import com.github.nothing2512.football_v2.utils.RecyclerViewAssertions
import com.github.nothing2512.football_v2.utils.rule.CountingExecutorRule
import com.github.nothing2512.football_v2.utils.rule.IdlingRule
import com.github.nothing2512.football_v2.utils.rule.KoinRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LovedEventFragmentTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(LovedActivity::class.java)

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
    fun loadEvents() {
        onView(withId(R.id.tvEvent)).perform(click())
        onView(withId(R.id.eventRecyclerView)).check(RecyclerViewAssertions(TestUtil.LOVED_COUNT))
    }
}