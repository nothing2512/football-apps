package com.github.nothing2512.football_v2.data.source.remote.response

import com.github.nothing2512.football_v2.data.source.local.entity.PlayerEntity

data class PlayerResponse(val players: List<PlayerEntity>)

data class PlayersResponse(val player: List<PlayerEntity>)