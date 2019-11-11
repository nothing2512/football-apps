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
class PlayerRepositoryTest: KoinTest {

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
    private lateinit var repository: PlayerRepository

    @Test
    fun loadPlayers() {
        val data = listOf(TestUtil.PLAYER_ENTITY)
        every { helper.listPlayer(TestUtil.INT) } returns data
        repository = PlayerRepository(appExecutors, helper, service)
        coroutineRule.runAsync {
            repository.getPlayerList(TestUtil.INT)
            coVerify { repository.getPlayerList(TestUtil.INT) }
            confirmVerified(helper)
            assertThat(repository.players.value?.data?.player, `is`(data))
        }
    }

    @Test
    fun loadPlayer() {
        val data = TestUtil.PLAYER_ENTITY
        every { helper.detailPlayer(TestUtil.INT) } returns data
        repository = PlayerRepository(appExecutors, helper, service)
        coroutineRule.runAsync {
            repository.getPlayerDetail(TestUtil.INT)
            coVerify { repository.getPlayerDetail(TestUtil.INT) }
            confirmVerified(helper)
            assertThat(repository.player.value?.data, `is`(data))
        }
    }
}