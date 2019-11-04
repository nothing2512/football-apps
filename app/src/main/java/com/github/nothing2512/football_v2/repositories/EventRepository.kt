package com.github.nothing2512.football_v2.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.anko.DatabaseHelper
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.local.entity.SearchEntity
import com.github.nothing2512.football_v2.data.source.remote.ApiResponse
import com.github.nothing2512.football_v2.data.source.remote.NetworkService
import com.github.nothing2512.football_v2.data.source.remote.response.EventResponse
import com.github.nothing2512.football_v2.data.source.remote.response.SearchResponse
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.utils.AppExecutors
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.EspressoIdlingResource
import com.github.nothing2512.football_v2.utils.NetworkBoundService
import com.github.nothing2512.football_v2.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OpenForTesting
class EventRepository(
    private val appExecutors: AppExecutors,
    private val helper: DatabaseHelper,
    private val service: NetworkService
) {

    val searchData = MutableLiveData<Resource<SearchResponse>>()
    val nextEventData = MutableLiveData<Resource<EventResponse>>()
    val previusEventData = MutableLiveData<Resource<EventResponse>>()
    val detailEvent = MutableLiveData<Resource<EventEntity>>()

    suspend fun getNextEvent(idLeague: Int) {

        val event = object :
            NetworkBoundService<EventResponse, EventResponse>(appExecutors) {
            override fun saveCallResult(item: EventResponse) {
                item.events?.forEach {
                    if (it.isFootball()) {
                        it.state = Constants.STATE_NEXT
                        helper.insert(it)
                    }
                }
            }

            override fun shouldFetch(data: EventResponse?): Boolean =
                data == null || data.events?.size == 0

            override fun loadFromDb(): LiveData<EventResponse> {
                val event = MutableLiveData<EventResponse>()

                helper.getNextEvent(idLeague)?.observeForever {
                    event.postValue(EventResponse(it))
                }

                return event
            }

            override fun createCall(): LiveData<ApiResponse<EventResponse>> =
                EspressoIdlingResource.handle {
                    service.nextEvent(idLeague)
                }

        }.asLiveData()

        event.observeForever { nextEventData.postValue(it) }
    }

    suspend fun getPreviusEvent(idLeague: Int) {

        val event = object :
            NetworkBoundService<EventResponse, EventResponse>(appExecutors) {
            override fun saveCallResult(item: EventResponse) {
                item.events?.forEach {
                    if (it.isFootball()) {
                        it.state = Constants.STATE_PREVIUS
                        helper.insert(it)
                    }
                }
            }

            override fun shouldFetch(data: EventResponse?): Boolean =
                data == null || data.events?.size == 0

            override fun loadFromDb(): LiveData<EventResponse> {
                val event = MutableLiveData<EventResponse>()

                helper.getPreviusEvent(idLeague)?.observeForever {
                    event.postValue(EventResponse(it))
                }

                return event
            }

            override fun createCall(): LiveData<ApiResponse<EventResponse>> =
                EspressoIdlingResource.handle {
                    service.previusEvent(idLeague)
                }

        }.asLiveData()

        event.observeForever { previusEventData.postValue(it) }
    }

    suspend fun searchEvent(query: String) {

        val search = object :
            NetworkBoundService<SearchResponse, SearchResponse>(appExecutors) {

            override fun saveCallResult(item: SearchResponse) {
                item.event?.forEach {
                    if (it.isFootball()) {
                        it.state = Constants.STATE_NEXT
                        helper.insert(SearchEntity(it.idEvent, query))
                        helper.insert(it)
                    }
                }
            }

            override fun shouldFetch(data: SearchResponse?): Boolean =
                data == null || data.event?.size == 0

            override fun loadFromDb(): LiveData<SearchResponse> {
                val event = MutableLiveData<SearchResponse>()

                helper.search(query)?.observeForever {
                    event.postValue(SearchResponse(it ?: ArrayList()))
                }

                return event
            }

            override fun createCall(): LiveData<ApiResponse<SearchResponse>> =
                EspressoIdlingResource.handle {
                    service.search(query)
                }

        }

        search.asLiveData().observeForever { searchData.postValue(it) }
    }

    suspend fun getDetail(idEvent: Int) {

        val event = object :
            NetworkBoundService<EventEntity, EventResponse>(appExecutors) {

            override fun saveCallResult(item: EventResponse) {
                item.events?.get(0)?.let {
                    helper.insert(it)
                }
            }

            override fun shouldFetch(data: EventEntity?): Boolean =
                data == null

            override fun loadFromDb(): LiveData<EventEntity> = helper.getEvent(idEvent) ?: MutableLiveData()

            override fun createCall(): LiveData<ApiResponse<EventResponse>> =
                EspressoIdlingResource.handle {
                    service.detailEvent(idEvent)
                }

        }.asLiveData()

        event.observeForever { detailEvent.postValue(it) }
    }

    suspend fun setLove(love: Boolean, event: EventEntity?) {
        withContext(Dispatchers.IO) {
            if (love) event?.love = 1
            else event?.love = 0
            helper.insert(event)
        }
    }

    suspend fun getLoved() = withContext(Dispatchers.IO) {
        helper.getLovedEvent()
    }
}