package com.github.nothing2512.football_v2.binding

import android.content.Context
import android.content.Intent
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.ui.league.LeagueActivity
import com.github.nothing2512.football_v2.utils.resources.Constants

data class LeagueItemBindingData(
    val name: String,
    val logo: Any,
    val desc: String,
    val onClick: () -> Unit
) {

    companion object {

        fun parse(context: Context, data: LeagueEntity) =
            LeagueItemBindingData(
                data.strLeague,
                data.strBadge as Any,
                data.strDescriptionEN ?: ""
            ) {
                context.startActivity(Intent(context, LeagueActivity::class.java).apply {
                    putExtra(Constants.EXTRA_ID, data.idLeague)
                })
            }
    }
}