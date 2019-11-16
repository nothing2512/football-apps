package com.github.nothing2512.football_v2.ui.team.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.github.nothing2512.football_v2.R
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
class SearchTeamActivityTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(SearchTeamActivity::class.java)

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
    fun searchTeam() {
        onView(withId(R.id.svTeams)).perform(SearchViewAction("arsenal"))
        countingExecutorRule.drainTasks(
            Constants.SERVICE_LATENCY_IN_MILLIS.toInt(),
            TimeUnit.MILLISECONDS
        )
        onView(withId(R.id.svTeamList)).check(RecyclerViewAssertions(1))
    }
}