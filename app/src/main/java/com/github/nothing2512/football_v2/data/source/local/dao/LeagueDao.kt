package com.github.nothing2512.football_v2.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.testing.OpenForTesting

@Dao
@OpenForTesting
interface LeagueDao {

    @Query("SELECT * FROM `LeagueEntity` WHERE `idLeague` = :idLeague")
    fun get(idLeague: Int): LiveData<LeagueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(league: LeagueEntity)

    @Query("UPDATE `LeagueEntity` SET `love` = :love WHERE `idLeague` = :idLeague")
    fun setLove(love: Boolean, idLeague: Int)

    @Query("SELECT * FROM `LeagueEntity` WHERE `love` = :love")
    fun getLoved(love: Boolean = true): LiveData<List<LeagueEntity>>
}