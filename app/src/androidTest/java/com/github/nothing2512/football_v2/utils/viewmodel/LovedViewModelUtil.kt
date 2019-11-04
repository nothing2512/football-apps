package com.github.nothing2512.football_v2.utils.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.testing.TestUtil
import com.github.nothing2512.football_v2.ui.loved.LovedViewModel
import com.github.nothing2512.football_v2.utils.repository.EventRepositoryUtil
import com.github.nothing2512.football_v2.utils.repository.LeagueRepositoryUtil

class LovedViewModelUtil : LovedViewModel(EventRepositoryUtil(), LeagueRepositoryUtil()) {

    override fun getEvents(): LiveData<List<EventEntity>> {
        val data = MutableLiveData<List<EventEntity>>()
        data.postValue(listOf(TestUtil.EVENT_ENTITY))
        return data
    }

    override fun getLeagues(): LiveData<List<LeagueEntity>> {
        val data = MutableLiveData<List<LeagueEntity>>()
        data.postValue(listOf(TestUtil.LEAGUE_ENTITY))
        return data
    }
}