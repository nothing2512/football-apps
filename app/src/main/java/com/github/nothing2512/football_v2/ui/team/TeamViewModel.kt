package com.github.nothing2512.football_v2.ui.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.nothing2512.football_v2.data.source.local.entity.TeamEntity
import com.github.nothing2512.football_v2.data.source.remote.response.PlayersResponse
import com.github.nothing2512.football_v2.data.source.remote.response.TeamResponse
import com.github.nothing2512.football_v2.repositories.PlayerRepository
import com.github.nothing2512.football_v2.repositories.TeamRepository
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.vo.Resource

@OpenForTesting
class TeamViewModel(
    private val repository: TeamRepository,
    private val playerRepository: PlayerRepository
): ViewModel() {

    fun loadPlayers(idTeam: Int): LiveData<Resource<PlayersResponse>> {
        val players = playerRepository.players
        launchMain { playerRepository.getPlayerList(idTeam) }
        return players
    }

    fun loadTeam(idTeam: Int): LiveData<Resource<TeamEntity>> {
        val team = repository.team
        launchMain { repository.getTeamDetail(idTeam) }
        return team
    }
}