package com.github.nothing2512.football_v2.ui.player

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.entity.PlayerEntity
import com.github.nothing2512.football_v2.repositories.PlayerRepository
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.util.CoroutineRule
import com.github.nothing2512.football_v2.util.KoinRule
import com.github.nothing2512.football_v2.vo.Resource
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class PlayerViewModelTest : KoinTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @Rule
    @JvmField
    val koinRule = KoinRule()

    private var repository = mockk<PlayerRepository>()
    private lateinit var viewModel: PlayerViewModel

    @Test
    fun loadPlayer() {

        val data = Resource.success(TestUtil.PLAYER_ENTITY)
        val player = MutableLiveData<Resource<PlayerEntity>>()
        player.value = data
        coEvery { repository.player } returns player
        coEvery { repository.getPlayerDetail(TestUtil.INT) } answers object : Answer<Unit> {
            override fun answer(call: Call) {}
        }
        viewModel = PlayerViewModel(repository)
        viewModel.loadPlayer(TestUtil.INT)
        coVerify { viewModel.loadPlayer(TestUtil.INT) }
        confirmVerified(repository)
        assertThat(viewModel.loadPlayer(TestUtil.INT).value, `is`(data))
    }
}