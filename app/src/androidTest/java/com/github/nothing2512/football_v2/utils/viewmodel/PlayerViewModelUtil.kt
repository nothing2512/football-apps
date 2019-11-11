package com.github.nothing2512.football_v2.utils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.entity.PlayerEntity
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.ui.player.PlayerViewModel
import com.github.nothing2512.football_v2.utils.repository.PlayerRepositoryUtil
import com.github.nothing2512.football_v2.vo.Resource

class PlayerViewModelUtil : PlayerViewModel(PlayerRepositoryUtil()) {

    override fun loadPlayer(idPlayer: Int): LiveData<Resource<PlayerEntity>> {
        val player = MutableLiveData<Resource<PlayerEntity>>()
        player.postValue(Resource.success(TestUtil.PLAYER_ENTITY))
        return player
    }
}