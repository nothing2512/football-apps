package com.github.nothing2512.football_v2.di

import com.github.nothing2512.football_v2.data.source.local.DatabaseHelper
import com.github.nothing2512.football_v2.data.source.local.FootballDatabase
import com.github.nothing2512.football_v2.data.source.remote.NetworkService
import com.github.nothing2512.football_v2.data.source.remote.adapter.CallAdapterFactory
import com.github.nothing2512.football_v2.repositories.EventRepository
import com.github.nothing2512.football_v2.repositories.LeagueRepository
import com.github.nothing2512.football_v2.repositories.PlayerRepository
import com.github.nothing2512.football_v2.repositories.TeamRepository
import com.github.nothing2512.football_v2.ui.event.EventViewModel
import com.github.nothing2512.football_v2.ui.favorite.FavoriteViewModel
import com.github.nothing2512.football_v2.ui.home.HomeViewModel
import com.github.nothing2512.football_v2.ui.league.LeagueViewModel
import com.github.nothing2512.football_v2.ui.player.PlayerViewModel
import com.github.nothing2512.football_v2.ui.team.TeamViewModel
import com.github.nothing2512.football_v2.utils.AppExecutors
import com.github.nothing2512.football_v2.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single { provideService() }
    single { FootballDatabase.getInstance(androidContext()) }
    single { DatabaseHelper(get()) }

    single { EventRepository(AppExecutors(), get(), get()) }
    single { LeagueRepository(AppExecutors(), get(), get()) }
    single { PlayerRepository(AppExecutors(), get(), get()) }
    single { TeamRepository(AppExecutors(), get(), get()) }

    viewModel { HomeViewModel() }
    viewModel { EventViewModel(get()) }
    viewModel { LeagueViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get(), get(), get()) }
    viewModel { PlayerViewModel(get()) }
    viewModel { TeamViewModel(get(), get()) }

}

private fun provideService() = Retrofit.Builder()
    .addCallAdapterFactory(CallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(Constants.BASE_URL)
    .build()
    .create(NetworkService::class.java)