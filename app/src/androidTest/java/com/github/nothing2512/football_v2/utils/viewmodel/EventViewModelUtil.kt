package com.github.nothing2512.football_v2.utils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.remote.response.EventResponse
import com.github.nothing2512.football_v2.data.source.remote.response.SearchResponse
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.ui.event.EventViewModel
import com.github.nothing2512.football_v2.utils.repository.EventRepositoryUtil
import com.github.nothing2512.football_v2.vo.Resource

class EventViewModelUtil : EventViewModel(EventRepositoryUtil()) {

    override fun loadNextEvent(idLeague: Int): LiveData<Resource<EventResponse>> {
        val events = MutableLiveData<Resource<EventResponse>>()
        events.postValue(Resource.success(TestUtil.EVENT_RESPONSE))
        return events
    }

    override fun loadPreviusEvent(idLeague: Int): LiveData<Resource<EventResponse>> {
        val events = MutableLiveData<Resource<EventResponse>>()
        events.postValue(Resource.success(TestUtil.EVENT_RESPONSE))
        return events
    }

    override fun searchEvent(query: String): LiveData<Resource<SearchResponse>> {
        val events = MutableLiveData<Resource<SearchResponse>>()
        events.postValue(
            when (query) {
                "arsenal" -> Resource.success(TestUtil.ARSENAL_RESPONSE)
                "barcelona" -> Resource.success(TestUtil.BARCELONA_RESPONSE)
                else -> Resource.success(SearchResponse(listOf()))
            }
        )
        return events
    }

    override fun getDetail(idEvent: Int): LiveData<Resource<EventEntity>> {
        val events = MutableLiveData<Resource<EventEntity>>()
        events.postValue(Resource.success(TestUtil.EVENT_ENTITY))
        return events
    }
}