package com.github.nothing2512.football_v2.utils.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.dao.LeagueDao
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity

class LeagueDaoUtil : LeagueDao {
    override fun get(idLeague: Int): LiveData<LeagueEntity> = MutableLiveData()

    override fun insert(league: LeagueEntity) {}

    override fun setLove(love: Boolean, idLeague: Int) {}

    override fun getLoved(love: Boolean): LiveData<List<LeagueEntity>> = MutableLiveData()

}