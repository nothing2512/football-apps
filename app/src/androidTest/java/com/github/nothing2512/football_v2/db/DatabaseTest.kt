package com.github.nothing2512.football_v2.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.nothing2512.football_v2.data.source.local.DatabaseHelper
import com.github.nothing2512.football_v2.data.source.local.entity.SearchEntity
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.utils.data.source.DatabaseUtil
import com.github.nothing2512.football_v2.utils.rule.CountingExecutorRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val countingExecutorRule = CountingExecutorRule()

    private lateinit var db: DatabaseUtil
    private lateinit var helper: DatabaseHelper

    @Before
    fun setUp() {

        db = DatabaseUtil(ApplicationProvider.getApplicationContext())
        helper = DatabaseHelper(db)
    }

    @Test
    fun leagueTest() {

        val league = TestUtil.LEAGUE_ENTITY
        helper.insert(league)
        val loaded = helper.getLeague(league.idLeague)
        assertThat(loaded, notNullValue())
        assertThat<String>(loaded?.strLeague, `is`<String>(league.strLeague))
    }

    @Test
    fun detailEventTest() {

        val event = TestUtil.EVENT_ENTITY
        helper.insert(event)
        val loaded = helper.getEvent(event.idEvent)
        assertThat(loaded, notNullValue())
        assertThat<String>(loaded?.strEvent, `is`<String>(event.strEvent))
    }

    @Test
    fun eventPreviusTest() {

        val events = TestUtil.PREVIUS_EVENT
        helper.insert(events)
        val loaded = helper.getPreviusEvent(events.idLeague)
        assertThat(loaded, notNullValue())
        assertThat<Int>(loaded?.size, `is`<Int>(1))
    }

    @Test
    fun eventNextTest() {

        val events = TestUtil.NEXT_EVENT
        helper.insert(events)
        val loaded = helper.getNextEvent(events.idLeague)
        assertThat(loaded, notNullValue())
        assertThat<Int>(loaded?.size, `is`<Int>(1))
    }

    @Test
    fun eventSearchTest() {

        val events = TestUtil.EVENT_ENTITY
        val query = events.strEvent
        helper.insert(events)
        helper.insert(SearchEntity(events.idEvent, query))
        val loaded = helper.search(query)
        assertThat(loaded, notNullValue())
        assertThat<Int>(loaded?.size, `is`<Int>(1))
    }

    @Test
    fun lovedEventTest() {

        val events = TestUtil.EVENT_ENTITY
        val id = events.idEvent
        helper.insert(events)
        events.love = 1
        helper.insert(events)
        val loaded = helper.getLovedEvent()
        assertThat(loaded, notNullValue())
    }

    @Test
    fun lovedLeagueTest() {

        val leagues = TestUtil.LEAGUE_ENTITY
        helper.insert(leagues)
        leagues.love = 1
        helper.insert(leagues)
        val loaded = helper.getLovedLeague()
        assertThat(loaded, notNullValue())
    }

    @After
    fun tearDown() {
        db.close()
        db.drop()
    }
}