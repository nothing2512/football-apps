package com.github.nothing2512.football_v2.ui.loved

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.repositories.EventRepository
import com.github.nothing2512.football_v2.repositories.LeagueRepository
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.ui.loved.fragment.LovedEventFragment
import com.github.nothing2512.football_v2.ui.loved.fragment.LovedLeagueFragment
import com.github.nothing2512.football_v2.utils.resources.Constants
import com.github.nothing2512.football_v2.utils.launchMain

@Suppress("PropertyName")
@OpenForTesting
class LovedViewModel constructor(
    private val eventRepository: EventRepository,
    private val leagueRepository: LeagueRepository
) : ViewModel() {

    val fragment = MutableLiveData<Fragment>().apply {
        postValue(LovedLeagueFragment.newInstance())
    }

    fun setFragment(state: Int) {

        when (state) {

            Constants.STATE_LEAGUE -> fragment.postValue(LovedLeagueFragment.newInstance())

            Constants.STATE_EVENT -> fragment.postValue(LovedEventFragment.newInstance())

            else -> throw IndexOutOfBoundsException("State count doesn't match")
        }
    }

    fun getEvents(): LiveData<List<EventEntity>> {

        val events = MutableLiveData<List<EventEntity>>()

        launchMain { events.postValue(eventRepository.getLoved()) }

        return events
    }

    fun getLeagues(): LiveData<List<LeagueEntity>> {

        val leagues = MutableLiveData<List<LeagueEntity>>()

        launchMain { leagues.postValue(leagueRepository.getLoved())}

        return leagues
    }
}