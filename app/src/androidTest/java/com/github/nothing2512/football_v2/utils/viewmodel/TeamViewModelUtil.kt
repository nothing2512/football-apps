package com.github.nothing2512.football_v2.utils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.entity.TeamEntity
import com.github.nothing2512.football_v2.data.source.remote.response.PlayersResponse
import com.github.nothing2512.football_v2.data.source.remote.response.TeamResponse
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.ui.team.TeamViewModel
import com.github.nothing2512.football_v2.utils.repository.PlayerRepositoryUtil
import com.github.nothing2512.football_v2.utils.repository.TeamRepositoryUtil
import com.github.nothing2512.football_v2.vo.Resource

class TeamViewModelUtil : TeamViewModel(TeamRepositoryUtil(), PlayerRepositoryUtil()) {

    override fun loadTeam(idTeam: Int): LiveData<Resource<TeamEntity>> {
        val team = MutableLiveData<Resource<TeamEntity>>()
        team.postValue(Resource.success(TestUtil.TEAM_ENTITY))
        return team
    }

    override fun loadPlayers(idTeam: Int): LiveData<Resource<PlayersResponse>> {
        val players = MutableLiveData<Resource<PlayersResponse>>()
        players.postValue(Resource.success(PlayersResponse(listOf(TestUtil.PLAYER_ENTITY))))
        return players
    }
}