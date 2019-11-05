package com.github.nothing2512.football_v2.ui.league

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.repositories.LeagueRepository
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.util.CoroutineRule
import com.github.nothing2512.football_v2.util.KoinRule
import com.github.nothing2512.football_v2.vo.Resource
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

    private var repository = mockk<LeagueRepository>()
    private lateinit var viewModel: LeagueViewModel

    @Test
    fun loadLeague() {

        val data = Resource.success(TestUtil.LEAGUE_ENTITY)
        val liveData = MutableLiveData<Resource<LeagueEntity>>()
        liveData.value = data
        coEvery { repository.league } returns liveData
        coEvery { repository.getDetail(TestUtil.INT) } answers object : Answer<Unit> {
            override fun answer(call: Call) {}
        }
        viewModel = LeagueViewModel(repository)
        viewModel.getDetail(TestUtil.INT)
        coVerify { viewModel.getDetail(TestUtil.INT) }
        confirmVerified(repository)
        assertThat(viewModel.getDetail(TestUtil.INT).value, `is`(data))

    }

    @Test
    fun loadLeagues() {
        val data = listOf(TestUtil.LEAGUE_ITEM_BINDING)
        val leagues = MutableLiveData<List<LeagueItemBindingData>>()
        leagues.value = data
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