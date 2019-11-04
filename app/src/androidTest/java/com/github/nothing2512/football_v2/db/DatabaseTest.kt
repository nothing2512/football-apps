package com.github.nothing2512.football_v2.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.nothing2512.football_v2.data.source.local.FootballDatabase
import com.github.nothing2512.football_v2.data.source.local.entity.SearchEntity
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.LiveDataTestUtil.getValue
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

    private lateinit var db: FootballDatabase

    @Before
    fun setUp() {

        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FootballDatabase::class.java
        ).build()
    }

    @Test
    fun leagueTest() {

        val league = TestUtil.LEAGUE_ENTITY
        db.leagueDao().insert(league)
        val loaded = getValue(db.leagueDao().get(league.idLeague))
        assertThat(loaded, notNullValue())
        assertThat<String>(loaded.strLeague, `is`<String>(league.strLeague))
    }

    @Test
    fun detailEventTest() {

        val event = TestUtil.EVENT_ENTITY
        db.eventDao().insert(event)
        val loaded = getValue(db.eventDao().get(event.idEvent))
        assertThat(loaded, notNullValue())
        assertThat<String>(loaded.strEvent, `is`<String>(event.strEvent))
    }

    @Test
    fun eventPreviusTest() {

        val events = TestUtil.PREVIUS_EVENT
        val idLeague = events.idLeague
        db.eventDao().insert(events)
        val loaded = getValue(db.eventDao().getInLeague(idLeague, Constants.STATE_PREVIUS))
        assertThat(loaded, notNullValue())
        assertThat<Int>(loaded.size, `is`<Int>(1))
    }

    @Test
    fun eventNextTest() {

        val events = TestUtil.NEXT_EVENT
        val idLeague = events.idLeague
        db.eventDao().insert(events)
        val loaded = getValue(db.eventDao().getInLeague(idLeague, Constants.STATE_NEXT))
        assertThat(loaded, notNullValue())
        assertThat<Int>(loaded.size, `is`<Int>(1))
    }

    @Test
    fun eventSearchTest() {

        val events = TestUtil.EVENT_ENTITY
        val query = events.strEvent
        db.eventDao().insert(events)
        db.eventDao().insert(SearchEntity(events.idEvent, query))
        val loaded = getValue(db.eventDao().search(query))
        assertThat(loaded, notNullValue())
        assertThat<Int>(loaded.size, `is`<Int>(1))
    }

    @Test
    fun lovedEventTest() {

        val events = TestUtil.EVENT_ENTITY
        val id = events.idEvent
        db.eventDao().insert(events)
        db.eventDao().setLove(true, id)
        val loaded = getValue(db.eventDao().getLoved())
        assertThat(loaded, notNullValue())
    }

    @Test
    fun lovedLeagueTest() {

        val leagues = TestUtil.LEAGUE_ENTITY
        val id = leagues.idLeague
        db.leagueDao().insert(leagues)
        db.leagueDao().setLove(true, id)
        val loaded = getValue(db.leagueDao().getLoved())
        assertThat(loaded, notNullValue())
    }

    @After
    fun tearDown() {
        db.close()
    }
}