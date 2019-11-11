package com.github.nothing2512.football_v2.data.source.local

import android.content.ContentValues
import com.github.nothing2512.football_v2.data.source.local.entity.*
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.utils.Constants
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync

@OpenForTesting
@Suppress("SameParameterValue")
class DatabaseHelper(private val footballDatabase: FootballDatabase?) {

    fun getEvent(idEvent: Int) = footballDatabase?.use {
        select("event").whereArgs(
            "idEvent = {idEvent}",
            "idEvent" to idEvent
        ).exec {
            val data = parseList<EventEntity>(classParser())
            if (data.isEmpty()) null else data[0]
        }
    }

    fun getLeague(idLeague: Int) = footballDatabase?.use {
        select("league").whereArgs(
            "idLeague = {idLeague}",
            "idLeague" to idLeague
        ).exec {
            val data = parseList<LeagueEntity>(classParser())
            if (data.isEmpty()) null else data[0]
        }
    }

    fun getKlasemen(idLeague: Int) = footballDatabase?.use {
        select("klasemen").whereArgs(
            "idLeague = {idLeague}",
            "idLeague" to idLeague
        ).exec { parseList<KlasemenEntity>(classParser()) }
    }

    fun listTeam(idLeague: Int) = footballDatabase?.use {
        select("team").whereArgs(
            "idLeague = {idLeague}",
            "idLeague" to idLeague
        ).exec { parseList<TeamEntity>(classParser()) }
    }

    fun detailTeam(idTeam: Int) = footballDatabase?.use {
        select("team").whereArgs(
            "idTeam = {idTeam}",
            "idTeam" to idTeam
        ).exec {
            val data = parseList<TeamEntity>(classParser())
            if (data.isEmpty()) null else data[0]
        }
    }

    fun listPlayer(idTeam: Int) = footballDatabase?.use {
        select("player").whereArgs(
            "idTeam = {idTeam}",
            "idTeam" to idTeam
        ).exec { parseList<PlayerEntity>(classParser()) }
    }

    fun detailPlayer(idPlayer: Int) = footballDatabase?.use {
        select("player").whereArgs(
            "idPlayer = {idPlayer}",
            "idPlayer" to idPlayer
        ).exec {
            val data = parseList<PlayerEntity>(classParser())
            if (data.isEmpty()) null else data[0]
        }
    }

    fun getNextEvent(idLeague: Int) = footballDatabase?.use {
        select("event").whereArgs(
            "idLeague = {idLeague} and state = {state}",
            "idLeague" to idLeague,
            "state" to Constants.STATE_NEXT
        ).exec { parseList<EventEntity>(classParser()) }
    }

    fun getPreviusEvent(idLeague: Int) = footballDatabase?.use {
        select("event").whereArgs(
            "idLeague = {idLeague} and state = {state}",
            "idLeague" to idLeague,
            "state" to Constants.STATE_PREVIUS
        ).exec { parseList<EventEntity>(classParser()) }
    }

    fun getFavoriteLeague() = footballDatabase?.use {
        select("league").whereArgs(
            "love = {love}",
            "love" to 1
        ).exec { parseList<LeagueEntity>(classParser()) }
    }

    fun getFavoriteEvent() = footballDatabase?.use {
        select("event").whereArgs(
            "love = {love}",
            "love" to 1
        ).exec { parseList<EventEntity>(classParser()) }
    }

    fun search(keyword: String) = footballDatabase?.use {

        val cursor = rawQuery(
            "SELECT e.* FROM event e, search s WHERE e.idEvent = s.idEVent AND s.keyword = ?",
            arrayOf(keyword)
        )
        val data = cursor.parseList<EventEntity>(classParser())
        cursor.close()
        data
    }

    fun insert(klasemen: KlasemenEntity) {
        insert("klasemen", null, klasemen.getValue())
    }

    fun insert(player: PlayerEntity) {
        insert("player", null, player.getValue())
    }

    fun insert(team: TeamEntity) {
        insert("team", null, team.getValue())
    }

    fun insert(search: SearchEntity) {
        insert("search", null, search.getValue())
    }

    fun insert(event: EventEntity?) {
        insert("event", null, event?.getValue())
    }

    final fun insert(league: LeagueEntity?) {
        insert("league", null, league?.getvalue())
    }

    private fun insert(table: String, columnHack: String?, value: ContentValues?) {
        doAsync {
            footballDatabase?.use {
                replace(table, columnHack, value)
            }
        }
    }
}