package com.github.nothing2512.football_v2.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.remote.response.EventResponse
import com.github.nothing2512.football_v2.data.source.remote.response.SearchResponse
import com.github.nothing2512.football_v2.repositories.EventRepository
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.vo.Resource

@OpenForTesting
class EventViewModel(private val repository: EventRepository) : ViewModel() {

    fun loadNextEvent(idLeague: Int): LiveData<Resource<EventResponse>> {
        val nextEventData = repository.nextEventData
        launchMain { repository.getNextEvent(idLeague) }
        return nextEventData
    }

    fun loadPreviusEvent(idLeague: Int): LiveData<Resource<EventResponse>> {
        val previusEventData = repository.previusEventData
        launchMain { repository.getPreviusEvent(idLeague) }
        return previusEventData
    }

    fun searchEvent(query: String): LiveData<Resource<SearchResponse>> {
        val searchData = repository.searchData
        launchMain { repository.searchEvent(query) }
        return searchData
    }

    fun getDetail(idEvent: Int): LiveData<Resource<EventEntity>> {
        val detailEvent = repository.detailEvent
        launchMain { repository.getDetail(idEvent) }
        return detailEvent
    }

    fun love(event: EventEntity?) {
        launchMain { repository.setLove(true, event) }
    }

    fun unlove(event: EventEntity?) {
        launchMain { repository.setLove(false, event) }
    }
}