package com.github.nothing2512.football_v2.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.data.source.local.DatabaseHelper
import com.github.nothing2512.football_v2.data.source.local.entity.PlayerEntity
import com.github.nothing2512.football_v2.data.source.remote.NetworkService
import com.github.nothing2512.football_v2.data.source.remote.response.PlayerResponse
import com.github.nothing2512.football_v2.data.source.remote.response.PlayersResponse
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.utils.AppExecutors
import com.github.nothing2512.football_v2.utils.EspressoIdlingResource
import com.github.nothing2512.football_v2.utils.NetworkBoundService
import com.github.nothing2512.football_v2.vo.Resource

@OpenForTesting
class PlayerRepository(
    private val appExecutors: AppExecutors,
    private val helper: DatabaseHelper,
    private val service: NetworkService
) {

    val player = MutableLiveData<Resource<PlayerEntity>>()
    val players = MutableLiveData<Resource<PlayersResponse>>()

    suspend fun getPlayerList(idTeam: Int) {

        val data = object : NetworkBoundService<PlayersResponse, PlayersResponse>(appExecutors) {

            override fun saveCallResult(item: PlayersResponse) {
                item.player.forEach { helper.insert(it) }
            }

            override fun shouldFetch(data: PlayersResponse?) =
                data?.player?.isEmpty() == true || data == null

            override fun loadFromDb(): LiveData<PlayersResponse> {
                val data = MutableLiveData<PlayersResponse>()
                data.postValue(PlayersResponse(helper.listPlayer(idTeam) ?: ArrayList()))
                return data
            }

            override fun createCall() = EspressoIdlingResource.handle {
                service.listPlayers(idTeam)
            }

            override fun onSuccessCall(item: PlayersResponse) = item
        }

        data.asLiveData().observeForever { players.postValue(it) }
    }

    suspend fun getPlayerDetail(idPlayer: Int) {

        val data = object : NetworkBoundService<PlayerEntity, PlayerResponse>(appExecutors) {
            override fun saveCallResult(item: PlayerResponse) {
                item.players.forEach { helper.insert(it) }
            }

            override fun shouldFetch(data: PlayerEntity?) = data == null

            override fun loadFromDb(): LiveData<PlayerEntity> {
                val data = MutableLiveData<PlayerEntity>()
                data.postValue(helper.detailPlayer(idPlayer))
                return data
            }

            override fun createCall() = EspressoIdlingResource.handle {
                service.detailPlayer(idPlayer)
            }

            override fun onSuccessCall(item: PlayerResponse) =
                if (item.players.isEmpty()) null else item.players[0]

        }

        data.asLiveData().observeForever { player.postValue(it) }
    }
}