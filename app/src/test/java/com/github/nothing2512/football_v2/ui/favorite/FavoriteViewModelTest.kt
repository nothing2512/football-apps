package com.github.nothing2512.football_v2.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.repositories.EventRepository
import com.github.nothing2512.football_v2.repositories.LeagueRepository
import com.github.nothing2512.football_v2.repositories.TeamRepository
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.util.CoroutineRule
import com.github.nothing2512.football_v2.util.KoinRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

@Suppress("LocalVariableName")
@ExperimentalCoroutinesApi
class FavoriteViewModelTest : KoinTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @Rule
    @JvmField
    val koinRule = KoinRule()

    private val leagueRepository = mockk<LeagueRepository>()
    private val eventRepository = mockk<EventRepository>()
    private val teamRepository = mockk<TeamRepository>()
    private lateinit var viewModel: FavoriteViewModel

    @Test
    fun setFragment() {

        val data = Fragment()
        val fragment = MutableLiveData<Fragment>()
        fragment.value = data
        viewModel = mockk()
        every { viewModel.fragment } returns fragment
        viewModel.fragment
        verify { viewModel.fragment }
        confirmVerified(viewModel)
        assertThat(viewModel.fragment.value, `is`(data))
    }

    @Test
    fun loadFavoriteLeagues() {

        val data = listOf(TestUtil.LEAGUE_ENTITY)
        coEvery { leagueRepository.getFavorite() } returns data
        viewModel = FavoriteViewModel(eventRepository, leagueRepository, teamRepository)
        viewModel.getLeagues()
        coVerify { leagueRepository.getFavorite() }
        confirmVerified(leagueRepository)
        assertThat(viewModel.getLeagues().value, `is`(data))
    }

    @Test
    fun loadFavoriteEvents() {

        val data = listOf(TestUtil.EVENT_ENTITY)
        coEvery { eventRepository.getFavorite() } returns data
        viewModel = FavoriteViewModel(eventRepository, leagueRepository, teamRepository)
        viewModel.getEvents()
        coVerify { eventRepository.getFavorite() }
        confirmVerified(eventRepository)
        assertThat(viewModel.getEvents().value, `is`(data))
    }

    @Test
    fun loadFavoriteTeams() {

        val data = listOf(TestUtil.TEAM_ENTITY)
        coEvery { teamRepository.getFavorite() } returns data
        viewModel = FavoriteViewModel(eventRepository, leagueRepository, teamRepository)
        viewModel.getTeams()
        coVerify { teamRepository.getFavorite() }
        confirmVerified(teamRepository)
        assertThat(viewModel.getTeams().value, `is`(data))
    }
}