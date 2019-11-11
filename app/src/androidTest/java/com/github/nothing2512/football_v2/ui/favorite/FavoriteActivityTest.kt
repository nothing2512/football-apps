package com.github.nothing2512.football_v2.ui.favorite

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.ui.favorite.fragment.FavoriteEventFragment
import com.github.nothing2512.football_v2.ui.favorite.fragment.FavoriteLeagueFragment
import com.github.nothing2512.football_v2.utils.rule.CountingExecutorRule
import com.github.nothing2512.football_v2.utils.rule.IdlingRule
import com.github.nothing2512.football_v2.utils.rule.KoinRule
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteActivityTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(FavoriteActivity::class.java)

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
        onView(withId(R.id.tvLeagueLoved)).perform(click())
        assertTrue(koinRule.lovedViewModel.fragment.value is FavoriteLeagueFragment)
    }

    @Test
    fun testEventFragment() {
        onView(withId(R.id.tvEvent)).perform((click()))
        assertTrue(koinRule.lovedViewModel.fragment.value is FavoriteEventFragment)
    }
}