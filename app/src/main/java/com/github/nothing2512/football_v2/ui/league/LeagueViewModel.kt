package com.github.nothing2512.football_v2.ui.league

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.data.source.remote.response.KlasemenResponse
import com.github.nothing2512.football_v2.data.source.remote.response.TeamResponse
import com.github.nothing2512.football_v2.repositories.LeagueRepository
import com.github.nothing2512.football_v2.repositories.TeamRepository
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.ui.event.fragments.EventNextFragment
import com.github.nothing2512.football_v2.ui.event.fragments.EventPreviusFragment
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.vo.Resource

@OpenForTesting
class LeagueViewModel(
    private val repository: LeagueRepository,
    private val teamRepository: TeamRepository
) : ViewModel() {

    private lateinit var _fragment: List<Fragment>

    val fragment = MutableLiveData<Fragment>()
    val stateNext = MutableLiveData<Drawable>()
    val statePrevius = MutableLiveData<Drawable>()

    fun setLeagueId(idLeague: Int) {

        _fragment = listOf(
            EventNextFragment.newInstance(idLeague),
            EventPreviusFragment.newInstance(idLeague)
        )
    }

    fun setFragment(activity: FragmentActivity, state: Int) {

        if (fragment.value != _fragment[state]) fragment.postValue(_fragment[state])

        when (state) {

            Constants.STATE_NEXT -> {
                stateNext.postValue(activity.getDrawable(R.drawable.state_right_enabled))
                statePrevius.postValue(activity.getDrawable(R.drawable.state_left_disabled))
            }

            Constants.STATE_PREVIUS -> {
                stateNext.postValue(activity.getDrawable(R.drawable.state_right_disabled))
                statePrevius.postValue(activity.getDrawable(R.drawable.state_left_enabled))
            }
        }
    }

    fun getDetail(idLeague: Int): LiveData<Resource<LeagueEntity>> {
        val league = repository.league
        launchMain { repository.getDetail(idLeague) }
        return league
    }

    fun getLeagues(context: Context?): LiveData<List<LeagueItemBindingData>> {
        val leagues = repository.leagues
        launchMain { repository.getLeagues(context) }
        return leagues
    }

    fun getKlasemen(idLeague: Int): LiveData<Resource<KlasemenResponse>> {
        val klasemen = repository.klasemen
        launchMain { repository.getKlasemen(idLeague) }
        return klasemen
    }

    fun love(league: LeagueEntity?) {
        launchMain { repository.setFavorite(true, league) }
    }

    fun unlove(league: LeagueEntity?) {
        launchMain { repository.setFavorite(false, league) }
    }

    fun loadTeams(idLeague: Int): LiveData<Resource<TeamResponse>> {
        val teams = teamRepository.teams
        launchMain { teamRepository.getTeamList(idLeague) }
        return teams
    }
}