package com.github.nothing2512.football_v2.ui.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.entity.TeamEntity
import com.github.nothing2512.football_v2.data.source.remote.response.PlayersResponse
import com.github.nothing2512.football_v2.repositories.PlayerRepository
import com.github.nothing2512.football_v2.repositories.TeamRepository
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.util.CoroutineRule
import com.github.nothing2512.football_v2.util.KoinRule
import com.github.nothing2512.football_v2.vo.Resource
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class TeamViewModelTest : KoinTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @Rule
    @JvmField
    val koinRule = KoinRule()

    private var repository = mockk<TeamRepository>()
    private var playerRepository = mockk<PlayerRepository>()
    private lateinit var viewModel: TeamViewModel

    @Test
    fun loadTeam() {

        val data = Resource.success(TestUtil.TEAM_ENTITY)
        val team = MutableLiveData<Resource<TeamEntity>>()
        team.value = data
        coEvery { repository.team } returns team
        coEvery { repository.getTeamDetail(TestUtil.INT) } answers object : Answer<Unit> {
            override fun answer(call: Call) {}
        }
        viewModel = TeamViewModel(repository, playerRepository)
        viewModel.loadTeam(TestUtil.INT)
        coVerify { viewModel.loadTeam(TestUtil.INT) }
        confirmVerified(repository)
        assertThat(viewModel.loadTeam(TestUtil.INT).value, `is`(data))
    }


    @Test
    fun loadPlayers() {

        val data = Resource.success(PlayersResponse(listOf(TestUtil.PLAYER_ENTITY)))
        val players = MutableLiveData<Resource<PlayersResponse>>()
        players.value = data
        coEvery { playerRepository.players } returns players
        coEvery { playerRepository.getPlayerList(TestUtil.INT) } answers object : Answer<Unit> {
            override fun answer(call: Call) {}
        }
        viewModel = TeamViewModel(repository, playerRepository)
        viewModel.loadPlayers(TestUtil.INT)
        coVerify { viewModel.loadPlayers(TestUtil.INT) }
        confirmVerified(playerRepository)
        assertThat(viewModel.loadPlayers(TestUtil.INT).value, Is.`is`(data))
    }
}