package com.github.nothing2512.football_v2.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.data.source.local.entity.SearchEntity
import com.github.nothing2512.football_v2.testing.OpenForTesting

@Dao
@OpenForTesting
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchEntity: SearchEntity)

    @Query("SELECT * FROM `evententity` WHERE `rowId` = :idEvent")
    fun get(idEvent: Int): LiveData<EventEntity>

    @Query("SELECT e.* FROM `evententity` e, `searchentity` s WHERE s.idEvent = e.idEvent AND s.keyword = :query")
    fun search(query: String): LiveData<List<EventEntity>>

    @Query("SELECT * FROM `evententity` WHERE `idLeague` = :idLeague AND `state` = :status")
    fun getInLeague(idLeague: Int, status: Int): LiveData<List<EventEntity>>

    @Query("UPDATE `EventEntity` SET `love` = :love WHERE `idEvent` = :idEvent")
    fun setLove(love: Boolean, idEvent: Int)

    @Query("SELECT * FROM `evententity` WHERE `love` = :love")
    fun getLoved(love: Boolean = true): LiveData<List<EventEntity>>
}