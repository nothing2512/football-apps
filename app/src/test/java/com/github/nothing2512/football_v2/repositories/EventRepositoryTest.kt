package com.github.nothing2512.football_v2.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.dao.EventDao
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.remote.NetworkService
import com.github.nothing2512.football_v2.testing.InstantAppExecutors
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.util.CoroutineRule
import com.github.nothing2512.football_v2.util.KoinRule
import com.github.nothing2512.football_v2.utils.Constants
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
    private val eventDao = mockk<EventDao>()
    private val service = mockk<NetworkService>()
    private lateinit var repository: EventRepository

    @Test
    fun loadEvent() {
        val data = TestUtil.EVENT_ENTITY
        val event = MutableLiveData<EventEntity>()
        event.value = data
        every { eventDao.get(TestUtil.INT) }
        repository = EventRepository(appExecutors, eventDao, service)
        suspend {
            repository.getDetail(TestUtil.INT)
            coVerify { repository.getDetail(TestUtil.INT) }
            confirmVerified(eventDao)
            assertThat(repository.detailEvent.value?.data, `is`(data))
        }
    }

    @Test
    fun loadNextEvent() {
        val data = listOf(TestUtil.EVENT_ENTITY)
        val events = MutableLiveData<List<EventEntity>>()
        events.value = data
        every { eventDao.getInLeague(TestUtil.INT, Constants.STATE_NEXT) }
        repository = EventRepository(appExecutors, eventDao, service)
        suspend {
            repository.getNextEvent(TestUtil.INT)
            coVerify { repository.getNextEvent(TestUtil.INT) }
            confirmVerified(eventDao)
            assertThat(repository.nextEventData.value?.data?.events, `is`(data))
        }
    }

    @Test
    fun loadPreviusEvent() {
        val data = listOf(TestUtil.EVENT_ENTITY)
        val events = MutableLiveData<List<EventEntity>>()
        events.value = data
        every { eventDao.getInLeague(TestUtil.INT, Constants.STATE_PREVIUS) }
        repository = EventRepository(appExecutors, eventDao, service)
        suspend {
            repository.getPreviusEvent(TestUtil.INT)
            coVerify { repository.getPreviusEvent(TestUtil.INT) }
            confirmVerified(eventDao)
            assertThat(repository.previusEventData.value?.data?.events, `is`(data))
        }
    }

    @Test
    fun searchEvent() {
        val data = listOf(TestUtil.EVENT_ENTITY)
        val events = MutableLiveData<List<EventEntity>>()
        events.value = data
        every { eventDao.search(TestUtil.STRING) }
        repository = EventRepository(appExecutors, eventDao, service)
        suspend {
            repository.searchEvent(TestUtil.STRING)
            coVerify { repository.searchEvent(TestUtil.STRING) }
            confirmVerified(eventDao)
            assertThat(repository.searchData.value?.data?.event, `is`(data))
        }
    }

    @Test
    fun loadLovedEvent() {
        val data = listOf(TestUtil.EVENT_ENTITY)
        val events = MutableLiveData<List<EventEntity>>()
        events.value = data
        every { eventDao.getLoved() }
        repository = EventRepository(appExecutors, eventDao, service)
        suspend {
            repository.getLoved()
            coVerify { repository.getLoved() }
            confirmVerified(eventDao)
            assertThat(repository.getLoved().value, `is`(data))
        }
    }
}