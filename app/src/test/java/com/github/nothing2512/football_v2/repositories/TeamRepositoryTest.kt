package com.github.nothing2512.football_v2.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class TeamRepositoryTest : KoinTest {

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
    private lateinit var repository: TeamRepository

    @Test
    fun loadTeams() {
        val data = listOf(TestUtil.TEAM_ENTITY)
        every { helper.listTeam(TestUtil.INT) } returns data
        repository = TeamRepository(appExecutors, helper, service)
        coroutineRule.runAsync {
            repository.getTeamList(TestUtil.INT)
            coVerify { repository.getTeamList(TestUtil.INT) }
            confirmVerified(helper)
            assertThat(repository.teams.value?.data?.teams, `is`(data))
        }
    }

    @Test
    fun loadTeam() {
        val data = TestUtil.TEAM_ENTITY
        every { helper.detailTeam(TestUtil.INT) } returns data
        repository = TeamRepository(appExecutors, helper, service)
        coroutineRule.runAsync {
            repository.getTeamDetail(TestUtil.INT)
            coVerify { repository.getTeamDetail(TestUtil.INT) }
            confirmVerified(helper)
            assertThat(repository.team.value?.data, `is`(data))
        }
    }
}