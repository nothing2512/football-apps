package com.github.nothing2512.football_v2.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.nothing2512.football_v2.data.source.local.entity.PlayerEntity
import com.github.nothing2512.football_v2.data.source.remote.response.PlayersResponse
import com.github.nothing2512.football_v2.repositories.PlayerRepository
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.vo.Resource

@OpenForTesting
class PlayerViewModel(private val repository: PlayerRepository): ViewModel() {

    fun loadPlayer(idPlayer: Int): LiveData<Resource<PlayerEntity>> {
        val player = repository.player
        launchMain { repository.getPlayerDetail(idPlayer) }
        return player
    }
}