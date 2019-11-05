package com.github.nothing2512.football_v2.utils.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.ui.league.LeagueViewModel
import com.github.nothing2512.football_v2.utils.repository.LeagueRepositoryUtil
import com.github.nothing2512.football_v2.vo.Resource

class LeagueViewModelUtil : LeagueViewModel(LeagueRepositoryUtil()) {

    override fun getDetail(idLeague: Int): LiveData<Resource<LeagueEntity>> {

        val league = MutableLiveData<Resource<LeagueEntity>>()
        league.postValue(Resource.success(TestUtil.LEAGUE_ENTITY))
        return league
    }

    override fun getLeagues(context: Context?): MutableLiveData<List<LeagueItemBindingData>> {
        val leagues = MutableLiveData<List<LeagueItemBindingData>>()
        leagues.postValue(listOf(TestUtil.LEAGUE_ITEM_BINDING))
        return leagues
    }
}