package com.github.nothing2512.football_v2.ui.league

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.LeagueBinding
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.databinding.ActivityLeagueBinding
import com.github.nothing2512.football_v2.utils.*
import com.github.nothing2512.football_v2.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class LeagueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeagueBinding

    private val leagueViewModel: LeagueViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchMain {
            binding = getBinding(R.layout.activity_league)
            subscribeUI()
        }
    }

    private fun subscribeUI() {

        val leagueId = intent.getIntExtra(Constants.EXTRA_ID, 0)

        binding.activity = this
        binding.lifecycleOwner = this
        binding.viewModel = leagueViewModel

        leagueViewModel.setLeagueId(leagueId)
        leagueViewModel.setFragment(this, Constants.STATE_NEXT)

        binding.swipe.setOnRefreshListener { getData(leagueId) }

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

                    val description = it.data?.strDescriptionEN ?: ""
                    val dialog = LeagueDialog(this, description, binding.root as ViewGroup)

                    binding.leagueData = LeagueBinding.parse(
                        it.data,
                        { dialog.show() },
                        { finish() },
                        applicationContext
                    )

                    setLove(it.data)

                    stopLoading()
                }
            }
        })
    }

    private fun setLove(league: LeagueEntity?) {
        val love = league?.love == 1

        if (love) binding.btLeagueLoved.bind(R.drawable.love_active, false)
        else binding.btLeagueLoved.bind(R.drawable.love_inactive, false)

        binding.btLeagueLoved.setOnClickListener {

            if (love) {
                leagueViewModel.unlove(league)
                league?.love = 0
            } else {
                leagueViewModel.love(league)
                league?.love = 1
            }
            setLove(league)
        }
    }

    private fun startLoading() {

        binding.scroll.hide()
        binding.divider.hide()
        binding.tvDescription.hide()
        binding.imContent.hide()
        binding.arrow.hide()

        binding.leagueShimmer.start()
    }

    private fun stopLoading() {

        binding.mainFrame.isFocusable = false
        binding.imBackground.requestFocus()
        binding.swipe.isRefreshing = false
        binding.scroll.show()
        binding.divider.show()
        binding.tvDescription.show()
        binding.imContent.show()
        binding.arrow.show()

        binding.leagueShimmer.stop()
    }
}
