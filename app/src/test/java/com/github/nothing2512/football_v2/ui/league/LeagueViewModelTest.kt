package com.github.nothing2512.football_v2.ui.league

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.dao.LeagueDao
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.data.source.remote.NetworkService
import com.github.nothing2512.football_v2.repositories.LeagueRepository
import com.github.nothing2512.football_v2.testing.InstantAppExecutors
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.util.CoroutineRule
import com.github.nothing2512.football_v2.util.KoinRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class LeagueViewModelTest : KoinTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @Rule
    @JvmField
    val koinRule = KoinRule()

    private val executor = InstantAppExecutors()
    private val leagueDao = mockk<LeagueDao>()
    private val service = mockk<NetworkService>()
    private lateinit var repository: LeagueRepository
    private lateinit var viewModel: LeagueViewModel

    @Test
    fun loadLeague() {

        val data = TestUtil.LEAGUE_ENTITY
        val league = MutableLiveData<LeagueEntity>()
        league.value = data
        every { leagueDao.get(TestUtil.INT) } returns league
        repository = LeagueRepository(executor, leagueDao, service)
        viewModel = LeagueViewModel(repository)
        viewModel.getDetail(TestUtil.INT)
        verify { leagueDao.get(TestUtil.INT) }
        confirmVerified(leagueDao)
        assertThat(viewModel.getDetail(TestUtil.INT).value?.data, `is`(data))

    }

    @Test
    fun loadLeagues() {
        val data = listOf(TestUtil.LEAGUE_ITEM_BINDING)
        val leagues = MutableLiveData<List<LeagueItemBindingData>>()
        leagues.value = data
        repository = mockk()
        every { repository.leagues } returns leagues
        coEvery { repository.getLeagues(null) } answers object : Answer<Unit> {
            override fun answer(call: Call) {}
        }
        viewModel = LeagueViewModel(repository)
        viewModel.getLeagues(null)
        verify { viewModel.getLeagues(null) }
        confirmVerified(repository)
        assertThat(viewModel.getLeagues(null).value, `is`(data))
    }
}