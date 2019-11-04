package com.github.nothing2512.football_v2.data.source.anko

import android.content.ContentValues
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.data.source.local.entity.SearchEntity
import com.github.nothing2512.football_v2.utils.Constants
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import org.jetbrains.anko.doAsync
import timber.log.Timber

class DatabaseHelper(private val footballDatabase: FootballDatabase?) {

    fun getEvent(idEvent: Int) = footballDatabase?.use {
        select("event").whereArgs(
            "idEvent = {idEvent}",
            "idEvent" to idEvent
        ).exec {
            val liveData = MutableLiveData<EventEntity>()
            val data = parseList<EventEntity>(classParser())
            if (data.isEmpty()) liveData.postValue(null)
            else liveData.postValue(data[0])
            liveData
        }
    }

    fun getLeague(idLeague: Int) = footballDatabase?.use {
        select("league").whereArgs(
            "idLeague = {idLeague}",
            "idLeague" to idLeague
        ).exec {
            val liveData = MutableLiveData<LeagueEntity>()
            val data = parseList<LeagueEntity>(classParser())
            if (data.isEmpty()) liveData.postValue(null)
            else liveData.postValue(data[0])
            liveData
        }
    }

    fun getNextEvent(idLeague: Int) = footballDatabase?.use {
        select("event").whereArgs(
            "idLeague = {idLeague} and state = {state}",
            "idLeague" to idLeague,
            "state" to Constants.STATE_NEXT
        ).exec {
            val liveData = MutableLiveData<List<EventEntity>>()
            liveData.postValue(parseList(classParser()))
            liveData
        }
    }

    fun getPreviusEvent(idLeague: Int) = footballDatabase?.use {
        select("event").whereArgs(
            "idLeague = {idLeague} and state = {state}",
            "idLeague" to idLeague,
            "state" to Constants.STATE_PREVIUS
        ).exec {
            val liveData = MutableLiveData<List<EventEntity>>()
            liveData.postValue(parseList(classParser()))
            liveData
        }
    }

    fun getLovedLeague() = footballDatabase?.use {
        select("league").whereArgs(
            "love = {love}",
            "love" to 1
        ).exec {
            val liveData = MutableLiveData<List<LeagueEntity>>()
            liveData.postValue(parseList(classParser()))
            liveData
        }
    }

    fun getLovedEvent() = footballDatabase?.use {
        select("event").whereArgs(
            "love = {love}",
            "love" to 1
        ).exec {
            val liveData = MutableLiveData<List<EventEntity>>()
            liveData.postValue(parseList(classParser()))
            liveData
        }
    }

    fun loveEvent(idEvent: Int) {
        footballDatabase?.use {
            update("event", "love" to 1)
                .whereArgs(
                    "idEvent = {idEvent}",
                    "idEvent" to idEvent
                )
        }
    }

    fun search(keyword: String) = footballDatabase?.use {

        val liveData = MutableLiveData<List<EventEntity>>()
        val cursor = rawQuery(
            "SELECT e.* FROM event e, search s WHERE e.idEvent = s.idEVent AND s.keyword = ?",
            arrayOf(keyword)
        )
        cursor.close()
        liveData
    }

    fun insert(search: SearchEntity) {
        insert("search", null, search.getValue())
    }

    fun insert(event: EventEntity?) {
        insert("event", null, event?.getValue())
    }

    fun insert(league: LeagueEntity?) {
        insert("league", null, league?.getvalue())
    }

    fun insert(table: String, columnHack: String?, value: ContentValues?) {
        doAsync {
            footballDatabase?.use {
                replace(table, columnHack, value)
            }
        }
    }
}