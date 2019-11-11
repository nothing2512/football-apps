package com.github.nothing2512.football_v2.data.source.remote.response

import com.github.nothing2512.football_v2.data.source.local.entity.KlasemenEntity

data class KlasemenResponse(
    val table: List<KlasemenEntity>
)