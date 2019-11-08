package com.github.nothing2512.football_v2.binding

import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.utils.TimeUtil
import com.github.nothing2512.football_v2.utils.resources.Constants

data class EventBinding(
    val strEvent: String,
    val strLeague: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val intAwayScore: String,
    val intHomeScore: String,
    val intRound: String,
    val dateEvent: String,
    val strTime: String,
    val strThumb: String,
    val onBackPress: () -> Unit,
    val onClick: () -> Unit
) {

    companion object {

        fun parse(
            eventEntity: EventEntity,
            onBackPress: () -> Unit,
            onClick: () -> Unit
        ): EventBinding {

            val date = TimeUtil.format(eventEntity.dateEvent, eventEntity.strTime)

            return EventBinding(
                eventEntity.strEvent,
                eventEntity.strLeague,
                eventEntity.strHomeTeam ?: "",
                eventEntity.strAwayTeam ?: "",
                eventEntity.intAwayScore.toString(),
                eventEntity.intHomeScore.toString(),
                eventEntity.intRound.toString(),
                date[Constants.DATE_COLUMN],
                date[Constants.TIME_COLUMN],
                eventEntity.strThumb ?: "",
                onBackPress,
                onClick
            )
        }
    }
}