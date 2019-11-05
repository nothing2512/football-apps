package com.github.nothing2512.football_v2.ui.league

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.ui.view.league.LeagueActivityUI
import com.github.nothing2512.football_v2.utils.*
import com.github.nothing2512.football_v2.utils.resources.Constants
import com.github.nothing2512.football_v2.vo.Status
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.setContentView
import org.koin.androidx.viewmodel.ext.android.viewModel


class LeagueActivity : AppCompatActivity() {

    private val leagueViewModel: LeagueViewModel by viewModel()
    private val league = MutableLiveData<LeagueEntity>()

    lateinit var swipe: SwipeRefreshLayout
    lateinit var btLeagueLoved: ImageView
    lateinit var scroll: NestedScrollView
    lateinit var divider: ImageView
    lateinit var tvDescription: TextView
    lateinit var imContent: ImageView
    lateinit var arrow: ImageView
    lateinit var imBackground: ImageView
    lateinit var mainFrame: FrameLayout
    lateinit var card: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LeagueActivityUI(league, leagueViewModel).setContentView(this)
        launchMain { subscribeUI() }
    }

    private fun subscribeUI() {

        val leagueId = intent.getIntExtra(Constants.EXTRA_ID, 0)

        leagueViewModel.setLeagueId(leagueId)
        leagueViewModel.setFragment(this, Constants.STATE_NEXT)

        swipe.setOnRefreshListener { getData(leagueId) }

        getData(leagueId)

    }

    private fun getData(leagueId: Int) {

        startLoading()
        leagueViewModel.getDetail(leagueId).observe(this, Observer {

            when (it.status) {

                Status.LOADING -> startLoading()

                Status.ERROR -> {

                    stopLoading()
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }

                Status.SUCCESS -> {

                    league.postValue(it.data)

                    setLove(it.data)

                    stopLoading()
                }
            }
        })
    }

    private fun setLove(data: LeagueEntity?) {

        val love = data?.love == 1

        if (love) btLeagueLoved.bindImage(R.drawable.love_active, false)
        else btLeagueLoved.bindImage(R.drawable.love_inactive, false)

        btLeagueLoved.setOnClickListener {

            if (love) {
                leagueViewModel.unlove(data)
                data?.love = 0
            } else {
                leagueViewModel.love(data)
                data?.love = 1
            }
            setLove(data)
        }
    }

    private fun startLoading() {
        card.hide()
    }

    private fun stopLoading() {

        mainFrame.clearFocus()
        imBackground.requestFocus()
        swipe.isRefreshing = false
        card.show()
    }
}
