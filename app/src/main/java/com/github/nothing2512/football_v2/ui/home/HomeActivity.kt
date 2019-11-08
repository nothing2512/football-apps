package com.github.nothing2512.football_v2.ui.home

import android.os.Bundle
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.nothing2512.football_v2.ui.view.HomeActivityUI
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.show
import org.jetbrains.anko.setContentView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    lateinit var swipe: SwipeRefreshLayout
    lateinit var searchView: SearchView
    lateinit var btBackHome: ImageView
    lateinit var imBackground: ImageView
    lateinit var content: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeActivityUI(homeViewModel).setContentView(this)
        launchMain { subscribeUI() }
    }

    private fun subscribeUI() {

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        swipe.setOnRefreshListener {

            homeViewModel.refresh()
            swipe.isRefreshing = false
            btBackHome.show()
            imBackground.requestFocus()
        }

        searchView.apply {

            setOnQueryTextFocusChangeListener { _, hasFocus ->

                if (hasFocus && query.isNullOrEmpty() && !searchView.isVisible)
                    clearFocus()
                if (!hasFocus && !btBackHome.isVisible) isIconified = true
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {

                    homeViewModel.setQuery(query ?: "")
                    homeViewModel.submitQuery()
                    if (!query.isNullOrEmpty()) btBackHome.show()
                    imBackground.requestFocus()
                    clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = true

            })
        }
    }

    override fun onBackPressed() {

        if (btBackHome.isVisible) {
            homeViewModel.setQuery("")
            homeViewModel.submitQuery()
            launchMain {
                btBackHome.hide()
                searchView.clearFocus()
                searchView.isIconified = true
            }
        } else finish()
    }
}
