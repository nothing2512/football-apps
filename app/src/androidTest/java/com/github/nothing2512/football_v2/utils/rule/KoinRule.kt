package com.github.nothing2512.football_v2.utils.rule

import com.github.nothing2512.football_v2.ui.event.EventViewModel
import com.github.nothing2512.football_v2.ui.home.HomeViewModel
import com.github.nothing2512.football_v2.ui.league.LeagueViewModel
import com.github.nothing2512.football_v2.ui.favorite.FavoriteViewModel
import com.github.nothing2512.football_v2.ui.player.PlayerViewModel
import com.github.nothing2512.football_v2.ui.team.TeamViewModel
import com.github.nothing2512.football_v2.utils.viewmodel.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@Suppress("MemberVisibilityCanBePrivate")
class KoinRule : TestWatcher() {

    val homeViewModel = HomeViewModelUtil()
    val leagueViewModel = LeagueViewModelUtil()
    val eventViewModel = EventViewModelUtil()
    val lovedViewModel = FavoriteViewModelUtil()
    val teamViewModel = TeamViewModelUtil()
    val playerViewModel = PlayerViewModelUtil()

    override fun starting(description: Description?) {
        super.starting(description)
        stopKoin()
        startKoin {
            modules(
                module {
                    viewModel { homeViewModel as HomeViewModel }
                    viewModel { leagueViewModel as LeagueViewModel }
                    viewModel { eventViewModel as EventViewModel }
                    viewModel { lovedViewModel as FavoriteViewModel }
                    viewModel { teamViewModel as TeamViewModel }
                    viewModel { playerViewModel as PlayerViewModel }
                }
            )
        }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        stopKoin()
    }
}