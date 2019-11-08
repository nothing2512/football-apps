package com.github.nothing2512.football_v2.ui.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.nothing2512.football_v2.testing.OpenForTesting
import com.github.nothing2512.football_v2.ui.event.fragments.SearchFragment
import com.github.nothing2512.football_v2.ui.league.LeagueFragment
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.resources.Constants

@OpenForTesting
class HomeViewModel : ViewModel() {

    private val fragments = arrayListOf(
        LeagueFragment.newInstance(),
        SearchFragment.newInstance()
    )

    val query = MutableLiveData<String?>()

    val fragment = MutableLiveData<Fragment>().apply { postValue(fragments[0]) }
    val title = MutableLiveData<String>().apply { postValue(Constants.LEAGUE_TITLE) }

    fun setQuery(query: String) {
        launchMain { this@HomeViewModel.query.value = query }
    }

    fun submitQuery() {
        launchMain {
            if (!query.value.isNullOrEmpty()) {

                val f = fragments[1] as SearchFragment
                if (fragment.value is LeagueFragment) fragment.postValue(f)
                title.postValue(Constants.SEARCH_TITLE)
                f.setQuery(query.value)
            } else if (fragment.value is SearchFragment) {
                fragment.postValue(fragments[0])
                title.postValue(Constants.LEAGUE_TITLE)
            }
        }
    }

    fun refresh() {
        launchMain {
            val f = fragment.value
            if (f is SearchFragment) f.setQuery(query.value)
        }
    }
}