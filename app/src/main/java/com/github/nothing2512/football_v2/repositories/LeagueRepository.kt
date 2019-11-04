package com.github.nothing2512.football_v2.repositories

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.anko.DatabaseHelper
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.data.source.remote.ApiResponse
import com.github.nothing2512.football_v2.data.source.remote.NetworkService
import com.github.nothing2512.football_v2.data.source.remote.response.LeagueResponse
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.ui.league.LeagueActivity
import com.github.nothing2512.football_v2.utils.AppExecutors
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.EspressoIdlingResource
import com.github.nothing2512.football_v2.utils.NetworkBoundService
import com.github.nothing2512.football_v2.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OpenForTesting
class LeagueRepository(
    private val appExecutors: AppExecutors,
    private val helper: DatabaseHelper,
    private val service: NetworkService
) {

    val leagues: MutableLiveData<List<LeagueItemBindingData>> = MutableLiveData()
    val league = MutableLiveData<Resource<LeagueEntity>>()

    suspend fun getDetail(idLeague: Int) {

        val data = object :
            NetworkBoundService<LeagueEntity, LeagueResponse>(appExecutors) {

            override fun saveCallResult(item: LeagueResponse) {
                helper.insert(item.leagues[0])
            }

            override fun shouldFetch(data: LeagueEntity?): Boolean =
                data == null


            override fun loadFromDb(): LiveData<LeagueEntity> =
                helper.getLeague(idLeague) ?: MutableLiveData()

            override fun createCall(): LiveData<ApiResponse<LeagueResponse>> =
                EspressoIdlingResource.handle {
                    service.detailLeague(idLeague)
                }


        }.asLiveData()

        data.observeForever { league.postValue(it) }
    }

    suspend fun getLeagues(context: Context?) = withContext(Dispatchers.Default) {
        val football = ArrayList<LeagueItemBindingData>()

        context?.resources?.let { resources ->
            val id = resources.getIntArray(R.array.football_id)
            val names = resources.getStringArray(R.array.football_name)
            val desc = resources.getStringArray(R.array.football_desc)
            val logo = resources.obtainTypedArray(R.array.football_logo)

            names.forEachIndexed { index, _ ->

                football.add(
                    LeagueItemBindingData(
                        names[index],
                        logo.getResourceId(index, R.mipmap.ic_launcher),
                        desc[index]
                    ) {
                        context.startActivity(Intent(context, LeagueActivity::class.java).apply {
                            putExtra(Constants.EXTRA_ID, id[index])
                        })
                    }
                )
            }

            logo.recycle()
        }

        leagues.postValue(football)
    }

    suspend fun setLoved(love: Boolean, league: LeagueEntity?) {
        withContext(Dispatchers.IO) {
            if (love) league?.love = 1
            else league?.love = 0
            helper.insert(league)
        }
    }

    suspend fun getLoved() = withContext(Dispatchers.IO) {
        helper.getLovedLeague()
    }
}