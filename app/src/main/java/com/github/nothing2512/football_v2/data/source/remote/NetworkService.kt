package com.github.nothing2512.football_v2.data.source.remote

import androidx.lifecycle.LiveData
import com.github.nothing2512.football_v2.data.source.remote.response.EventResponse
import com.github.nothing2512.football_v2.data.source.remote.response.LeagueResponse
import com.github.nothing2512.football_v2.data.source.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("lookupleague.php")
    fun detailLeague(@Query("id") leagueId: Int?): LiveData<ApiResponse<LeagueResponse>>

    @GET("eventsnextleague.php")
    fun nextEvent(@Query("id") leagueId: Int?): LiveData<ApiResponse<EventResponse>>

    @GET("eventspastleague.php")
    fun previusEvent(@Query("id") leagueId: Int?): LiveData<ApiResponse<EventResponse>>

    @GET("lookupevent.php")
    fun detailEvent(@Query("id") eventId: Int?): LiveData<ApiResponse<EventResponse>>

    @GET("searchevents.php")
    fun search(@Query("e") query: String?): LiveData<ApiResponse<SearchResponse>>
}