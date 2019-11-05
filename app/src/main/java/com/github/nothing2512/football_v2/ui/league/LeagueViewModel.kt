package com.github.nothing2512.football_v2.ui.league

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.repositories.LeagueRepository
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.ui.event.fragments.EventNextFragment
import com.github.nothing2512.football_v2.ui.event.fragments.EventPreviusFragment
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.vo.Resource

@OpenForTesting
class LeagueViewModel(
    private val repository: LeagueRepository
) : ViewModel() {

    private lateinit var _fragment: List<Fragment>

    val fragment = MutableLiveData<Fragment>()

    fun setLeagueId(idLeague: Int) {

        _fragment = listOf(
            EventNextFragment.newInstance(idLeague),
            EventPreviusFragment.newInstance(idLeague)
        )
    }

    fun setFragment(activity: FragmentActivity, state: Int) {

        if (fragment.value != _fragment[state]) fragment.postValue(_fragment[state])
    }

    fun getDetail(idLeague: Int): LiveData<Resource<LeagueEntity>> {

        val league = repository.league

        launchMain { repository.getDetail(idLeague) }

        return league
    }

    fun getLeagues(context: Context?): MutableLiveData<List<LeagueItemBindingData>> {

        val leagues = repository.leagues

        launchMain { repository.getLeagues(context) }

        return leagues
    }

    fun love(league: LeagueEntity?) {
        launchMain { repository.setLoved(true, league) }
    }

    fun unlove(league: LeagueEntity?) {
        launchMain { repository.setLoved(false, league) }
    }
}