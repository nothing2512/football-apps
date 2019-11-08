package com.github.nothing2512.football_v2.ui.league

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.ui.home.HomeActivity
import com.github.nothing2512.football_v2.utils.RecyclerViewAssertions
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.rule.CountingExecutorRule
import com.github.nothing2512.football_v2.utils.rule.IdlingRule
import com.github.nothing2512.football_v2.utils.rule.KoinRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LeagueFragmentTest {

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
    fun loadLeague() {
        onView(withId(Id.leagueRecyclerView)).check(RecyclerViewAssertions(TestUtil.LEAGUE_BINDING_COUNT))
    }
}