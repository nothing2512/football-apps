package com.github.nothing2512.football_v2.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.DatabaseHelper
import com.github.nothing2512.football_v2.data.source.local.entity.SearchTeamEntity
import com.github.nothing2512.football_v2.data.source.local.entity.TeamEntity
import com.github.nothing2512.football_v2.data.source.remote.ApiResponse
import com.github.nothing2512.football_v2.data.source.remote.NetworkService
import com.github.nothing2512.football_v2.data.source.remote.response.TeamResponse
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.utils.AppExecutors
import com.github.nothing2512.football_v2.utils.EspressoIdlingResource
import com.github.nothing2512.football_v2.utils.NetworkBoundService
import com.github.nothing2512.football_v2.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OpenForTesting
class TeamRepository(
    private val appExecutors: AppExecutors,
    private val helper: DatabaseHelper,
    private val service: NetworkService
) {

    val teams = MutableLiveData<Resource<TeamResponse>>()
    val team = MutableLiveData<Resource<TeamEntity>>()
    val searchData = MutableLiveData<Resource<TeamResponse>>()

    suspend fun getTeamList(idLeague: Int) {

        val data = object : NetworkBoundService<TeamResponse, TeamResponse>(appExecutors) {
            override fun saveCallResult(item: TeamResponse) {
                item.teams.forEach { helper.insert(it) }
            }

            override fun shouldFetch(data: TeamResponse?) =
                data?.teams?.isEmpty() == true || data == null

            override fun loadFromDb(): LiveData<TeamResponse> {
                val data = MutableLiveData<TeamResponse>()
                data.postValue(TeamResponse(helper.listTeam(idLeague) ?: ArrayList()))
                return data
            }

            override fun createCall() = EspressoIdlingResource.handle {
                service.listTeam(idLeague)
            }

            override fun onSuccessCall(item: TeamResponse) = item
        }

        data.asLiveData().observeForever { teams.postValue(it) }
    }

    suspend fun getTeamDetail(idTeam: Int) {

        val data = object : NetworkBoundService<TeamEntity, TeamResponse>(appExecutors) {
            override fun saveCallResult(item: TeamResponse) {
                item.teams.forEach { helper.insert(it) }
            }

            override fun shouldFetch(data: TeamEntity?) =
                data == null

            override fun loadFromDb(): LiveData<TeamEntity> {
                val data = MutableLiveData<TeamEntity>()
                data.postValue(helper.detailTeam(idTeam))
                return data
            }

            override fun createCall() = EspressoIdlingResource.handle {
                service.detailTeam(idTeam)
            }

            override fun onSuccessCall(item: TeamResponse) =
                if (item.teams.isEmpty()) null else item.teams[0]

        }

        data.asLiveData().observeForever { team.postValue(it) }
    }

    suspend fun searchTeams(keyword: String) {

        val data = object: NetworkBoundService<TeamResponse, TeamResponse>(appExecutors) {

            override fun saveCallResult(item: TeamResponse) {
                item.teams.forEach {
                    helper.insert(it)
                    helper.insert(SearchTeamEntity(it.idTeam, keyword))
                }
            }

            override fun shouldFetch(data: TeamResponse?) =
                data == null || data.teams.isEmpty()

            override fun loadFromDb(): LiveData<TeamResponse> {
                val teams = MutableLiveData<TeamResponse>()
                teams.postValue(TeamResponse(helper.searchTeams(keyword) ?: ArrayList()))
                return teams
            }

            override fun createCall() = EspressoIdlingResource.handle {
                service.searchTeams(keyword)
            }

            override fun onSuccessCall(item: TeamResponse) = item
        }

        data.asLiveData().observeForever { searchData.postValue(it) }
    }

    suspend fun setFavorite(love: Boolean, team: TeamEntity?) {
        withContext(Dispatchers.IO) {
            if (love) team?.love = 1
            else team?.love = 0
            helper.insert(team)
        }
    }

    suspend fun getFavorite() = withContext(Dispatchers.IO) {
        helper.getFavoriteTeam()
    }
}