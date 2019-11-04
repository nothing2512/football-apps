package com.github.nothing2512.football_v2.ui.event

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.remote.response.EventResponse
import com.github.nothing2512.football_v2.data.source.remote.response.SearchResponse
import com.github.nothing2512.football_v2.repositories.EventRepository
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.util.CoroutineRule
import com.github.nothing2512.football_v2.util.KoinRule
import com.github.nothing2512.football_v2.vo.Resource
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class EventViewModelTest : KoinTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineRule = CoroutineRule()

    @Rule
    @JvmField
    val koinRule = KoinRule()

    private val repository = mockk<EventRepository>()
    private lateinit var viewModel: EventViewModel

    @Test
    fun loadEvent() {

        val data = Resource.success(TestUtil.EVENT_ENTITY)
        val events = MutableLiveData<Resource<EventEntity>>()
        events.value = data
        every { repository.detailEvent } returns events
        coEvery { repository.getDetail(TestUtil.INT) } answers object : Answer<Unit> {
            override fun answer(call: Call) {}
        }
        viewModel = EventViewModel(repository)
        viewModel.getDetail(TestUtil.INT)
        verify { viewModel.getDetail(TestUtil.INT) }
        confirmVerified(repository)
        assertThat(viewModel.getDetail(TestUtil.INT).value, `is`(data))
    }

    @Test
    fun loadNextEvent() {

        val data = Resource.success(TestUtil.EVENT_RESPONSE)
        val events = MutableLiveData<Resource<EventResponse>>()
        events.value = data
        every { repository.nextEventData } returns events
        coEvery { repository.getNextEvent(TestUtil.INT) } answers object : Answer<Unit> {
            override fun answer(call: Call) {}
        }
        viewModel = EventViewModel(repository)
        viewModel.loadNextEvent(TestUtil.INT)
        verify { viewModel.loadNextEvent(TestUtil.INT) }
        confirmVerified(repository)
        assertThat(viewModel.loadNextEvent(TestUtil.INT).value, `is`(data))
    }

    @Test
    fun loadPreviusEvent() {

        val data = Resource.success(TestUtil.EVENT_RESPONSE)
        val events = MutableLiveData<Resource<EventResponse>>()
        events.value = data
        every { repository.previusEventData } returns events
        coEvery { repository.getPreviusEvent(TestUtil.INT) } answers object : Answer<Unit> {
            override fun answer(call: Call) {}
        }
        viewModel = EventViewModel(repository)
        viewModel.loadPreviusEvent(TestUtil.INT)
        verify { viewModel.loadPreviusEvent(TestUtil.INT) }
        confirmVerified(repository)
        assertThat(viewModel.loadPreviusEvent(TestUtil.INT).value, `is`(data))
    }

    @Test
    fun searchEvent() {
        val data = Resource.success(TestUtil.SEARCH_RESPONSE)
        val events = MutableLiveData<Resource<SearchResponse>>()
        events.value = data
        every { repository.searchData } returns events
        coEvery { repository.searchEvent(TestUtil.STRING) } answers object : Answer<Unit> {
            override fun answer(call: Call) {}
        }
        viewModel = EventViewModel(repository)
        viewModel.searchEvent(TestUtil.STRING)
        verify { viewModel.searchEvent(TestUtil.STRING) }
        confirmVerified(repository)
        assertThat(viewModel.searchEvent(TestUtil.STRING).value, `is`(data))
    }
}