package com.github.nothing2512.football_v2.utils.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.remote.ApiResponse
import com.github.nothing2512.football_v2.data.source.remote.NetworkService
import com.github.nothing2512.football_v2.data.source.remote.response.EventResponse
import com.github.nothing2512.football_v2.data.source.remote.response.LeagueResponse
import com.github.nothing2512.football_v2.data.source.remote.response.SearchResponse

class NetworkServiceUtil : NetworkService {
    override fun detailLeague(leagueId: Int?): LiveData<ApiResponse<LeagueResponse>> = MutableLiveData()

    override fun nextEvent(leagueId: Int?): LiveData<ApiResponse<EventResponse>> = MutableLiveData()

    override fun previusEvent(leagueId: Int?): LiveData<ApiResponse<EventResponse>> = MutableLiveData()

    override fun detailEvent(eventId: Int?): LiveData<ApiResponse<EventResponse>> = MutableLiveData()

    override fun search(query: String?): LiveData<ApiResponse<SearchResponse>> = MutableLiveData()

}