package com.github.nothing2512.football_v2.utils.viewmodel

import com.github.nothing2512.football_v2.ui.home.HomeViewModel

class HomeViewModelUtil : HomeViewModel() {

    override fun setQuery(query: String) {
        super.setQuery(query)
        this.query.value = query
    }
}