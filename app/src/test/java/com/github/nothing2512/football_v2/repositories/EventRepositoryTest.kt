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
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class EventRepositoryTest : KoinTest {

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
    private lateinit var repository: EventRepository

    @Test
    fun loadEvent() {
        val data = TestUtil.EVENT_ENTITY
        every { helper.getEvent(TestUtil.INT) }
        repository = EventRepository(appExecutors, helper, service)
        coroutineRule.runAsync {
            repository.getDetail(TestUtil.INT)
            coVerify { repository.getDetail(TestUtil.INT) }
            confirmVerified(helper)
            assertThat(repository.detailEvent.value?.data, `is`(data))
        }
    }

    @Test
    fun loadNextEvent() {
        val data = listOf(TestUtil.EVENT_ENTITY)
        every { helper.getNextEvent(TestUtil.INT) }
        repository = EventRepository(appExecutors, helper, service)
        coroutineRule.runAsync {
            repository.getNextEvent(TestUtil.INT)
            coVerify { repository.getNextEvent(TestUtil.INT) }
            confirmVerified(helper)
            assertThat(repository.nextEventData.value?.data?.events, `is`(data))
        }
    }

    @Test
    fun loadPreviusEvent() {
        val data = listOf(TestUtil.EVENT_ENTITY)
        every { helper.getPreviusEvent(TestUtil.INT) }
        repository = EventRepository(appExecutors, helper, service)
        coroutineRule.runAsync {
            repository.getPreviusEvent(TestUtil.INT)
            coVerify { repository.getPreviusEvent(TestUtil.INT) }
            confirmVerified(helper)
            assertThat(repository.previusEventData.value?.data?.events, `is`(data))
        }
    }

    @Test
    fun searchEvent() {
        val data = listOf(TestUtil.EVENT_ENTITY)
        every { helper.search(TestUtil.STRING) }
        repository = EventRepository(appExecutors, helper, service)
        coroutineRule.runAsync {
            repository.searchEvent(TestUtil.STRING)
            coVerify { repository.searchEvent(TestUtil.STRING) }
            confirmVerified(helper)
            assertThat(repository.searchData.value?.data?.event, `is`(data))
        }
    }

    @Test
    fun loadFavoriteEvent() {
        val data = listOf(TestUtil.EVENT_ENTITY)
        every { helper.getFavoriteEvent() }
        repository = EventRepository(appExecutors, helper, service)
        coroutineRule.runAsync {
            repository.getFavorite()
            coVerify { repository.getFavorite() }
            confirmVerified(helper)
            assertThat(repository.getFavorite(), `is`(data))
        }
    }
}