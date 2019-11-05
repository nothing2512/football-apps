package com.github.nothing2512.football_v2.ui.loved

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.repositories.EventRepository
import com.github.nothing2512.football_v2.repositories.LeagueRepository
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
class LovedViewModelTest : KoinTest {

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
    private lateinit var viewModel: LovedViewModel

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
    fun loadLeagues() {

        val data = listOf(TestUtil.LEAGUE_ENTITY)
        coEvery { leagueRepository.getLoved() } returns data
        viewModel = LovedViewModel(eventRepository, leagueRepository)
        viewModel.getLeagues()
        coVerify { leagueRepository.getLoved() }
        confirmVerified(leagueRepository)
        assertThat(viewModel.getLeagues().value, `is`(data))
    }

    @Test
    fun loadEvents() {

        val data = listOf(TestUtil.EVENT_ENTITY)
        coEvery { eventRepository.getLoved() } returns data
        viewModel = LovedViewModel(eventRepository, leagueRepository)
        viewModel.getEvents()
        coVerify { eventRepository.getLoved() }
        confirmVerified(eventRepository)
        assertThat(viewModel.getEvents().value, `is`(data))
    }
}