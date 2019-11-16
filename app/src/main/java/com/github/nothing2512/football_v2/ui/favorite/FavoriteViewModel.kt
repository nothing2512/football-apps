package com.github.nothing2512.football_v2.ui.favorite

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.data.source.local.entity.TeamEntity
import com.github.nothing2512.football_v2.repositories.EventRepository
import com.github.nothing2512.football_v2.repositories.LeagueRepository
import com.github.nothing2512.football_v2.repositories.TeamRepository
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.ui.favorite.fragment.FavoriteEventFragment
import com.github.nothing2512.football_v2.ui.favorite.fragment.FavoriteLeagueFragment
import com.github.nothing2512.football_v2.ui.favorite.fragment.FavoriteTeamFragment
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.launchMain

@Suppress("PropertyName")
@OpenForTesting
class FavoriteViewModel constructor(
    private val eventRepository: EventRepository,
    private val leagueRepository: LeagueRepository,
    private val teamRepository: TeamRepository
) : ViewModel() {

    val fragment = MutableLiveData<Fragment>().apply {
        postValue(FavoriteLeagueFragment.newInstance())
    }

    fun setFragment(state: Int) {

        when (state) {

            Constants.STATE_LEAGUE -> fragment.postValue(FavoriteLeagueFragment.newInstance())

            Constants.STATE_EVENT -> fragment.postValue(FavoriteEventFragment.newInstance())

            Constants.STATE_TEAM -> fragment.postValue(FavoriteTeamFragment.newInstance())

            else -> throw IndexOutOfBoundsException("State count doesn't match")
        }
    }

    fun getEvents(): LiveData<List<EventEntity>> {

        val events = MutableLiveData<List<EventEntity>>()

        launchMain { events.postValue(eventRepository.getFavorite()) }

        return events
    }

    fun getLeagues(): LiveData<List<LeagueEntity>> {

        val leagues = MutableLiveData<List<LeagueEntity>>()

        launchMain { leagues.postValue(leagueRepository.getFavorite()) }

        return leagues
    }

    fun getTeams(): LiveData<List<TeamEntity>> {

        val teams = MutableLiveData<List<TeamEntity>>()

        launchMain { teams.postValue(teamRepository.getFavorite()) }

        return teams
    }
}