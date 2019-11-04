package com.github.nothing2512.football_v2.utils.viewmodel

import com.github.nothing2512.football_v2.ui.event.fragments.SearchFragment
import com.github.nothing2512.football_v2.ui.home.HomeViewModel
import com.github.nothing2512.football_v2.ui.league.LeagueFragment

class HomeViewModelUtil : HomeViewModel() {

    override fun submitQuery() {
        if (query.value.isNullOrEmpty()) {
            fragment.postValue(LeagueFragment.newInstance())
        } else {
            fragment.postValue(SearchFragment.newInstance())
        }
    }
}