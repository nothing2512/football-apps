package com.github.nothing2512.football_v2.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.nothing2512.football_v2.data.source.local.dao.EventDao
import com.github.nothing2512.football_v2.data.source.local.dao.LeagueDao
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.data.source.local.entity.SearchEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        LeagueEntity::class,
        EventEntity::class,
        SearchEntity::class
    ]
)
abstract class FootballDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao

    abstract fun eventDao(): EventDao
}