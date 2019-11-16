package com.github.nothing2512.football_v2.data.source.remote

import androidx.lifecycle.LiveData
import com.github.nothing2512.football_v2.data.source.remote.response.*
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

    @GET("lookup_all_teams.php")
    fun listTeam(@Query("id") idLeague: Int): LiveData<ApiResponse<TeamResponse>>

    @GET("lookupteam.php")
    fun detailTeam(@Query("id") idTeam: Int): LiveData<ApiResponse<TeamResponse>>

    @GET("lookuptable.php")
    fun getKlasemen(@Query("l") idLeague: Int): LiveData<ApiResponse<KlasemenResponse>>

    @GET("lookup_all_players.php")
    fun listPlayers(@Query("id") idTeam: Int): LiveData<ApiResponse<PlayersResponse>>

    @GET("lookupplayer.php")
    fun detailPlayer(@Query("id") idPlayer: Int): LiveData<ApiResponse<PlayerResponse>>

    @GET("searchteams.php")
    fun searchTeams(@Query("t") keyword: String): LiveData<ApiResponse<TeamResponse>>
}