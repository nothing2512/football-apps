package com.github.nothing2512.football_v2.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.DatabaseHelper
import com.github.nothing2512.football_v2.data.source.remote.NetworkService
import com.github.nothing2512.football_v2.testing.InstantAppExecutors
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.util.CoroutineRule
import com.github.nothing2512.football_v2.util.KoinRule
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class LeagueRepositoryTest : KoinTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @Rule
    @JvmField
    val koinRule = KoinRule()

    private val appExecutors = InstantAppExecutors()
    private val helper = mockk<DatabaseHelper>()
    private val service = mockk<NetworkService>()
    private lateinit var repository: LeagueRepository

    @Test
    fun loadLeague() {
        val data = TestUtil.LEAGUE_ENTITY
        every { helper.getLeague(TestUtil.INT) } returns data
        repository = LeagueRepository(appExecutors, helper, service)
        suspend {
            repository.getDetail(TestUtil.INT)
            coVerify { repository.getDetail(TestUtil.INT) }
            confirmVerified(helper)
            assertThat(repository.league.value?.data, `is`(data))
        }
    }

    @Test
    fun loadLeagues() {
        val data = listOf(TestUtil.LEAGUE_ITEM_BINDING)
        val leagues = MutableLiveData<List<LeagueItemBindingData>>()
        leagues.value = data
        repository = mockk()
        every { repository.leagues } returns leagues
        suspend {
            repository.getLeagues(null)
            coVerify { repository.getLeagues(null) }
            confirmVerified(repository)
            assertThat(repository.leagues.value, `is`(data))
        }
    }

    @Test
    fun loadLovedLeagues() {
        val data = listOf(TestUtil.LEAGUE_ENTITY)
        every { helper.getLovedLeague() } returns data
        repository = LeagueRepository(appExecutors, helper, service)
        suspend {
            repository.getLoved()
            coVerify { repository.getLoved() }
            confirmVerified(helper)
            assertThat(repository.getLoved(), `is`(data))
        }
    }
}