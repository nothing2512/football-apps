package com.github.nothing2512.football_v2.utils.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.dao.EventDao
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.local.entity.SearchEntity

class EventDaoUtil : EventDao {

    override fun insert(event: EventEntity) {}

    override fun insert(searchEntity: SearchEntity) {}

    override fun get(idEvent: Int): LiveData<EventEntity> = MutableLiveData()

    override fun search(query: String): LiveData<List<EventEntity>> = MutableLiveData()

    override fun getInLeague(idLeague: Int, status: Int): LiveData<List<EventEntity>> = MutableLiveData()

    override fun setLove(love: Boolean, idEvent: Int) {}

    override fun getLoved(love: Boolean): LiveData<List<EventEntity>> = MutableLiveData()

}