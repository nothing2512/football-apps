package com.github.nothing2512.football_v2.ui.league

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.LeagueBinding
import com.github.nothing2512.football_v2.data.source.local.entity.KlasemenEntity
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.databinding.ActivityLeagueBinding
import com.github.nothing2512.football_v2.ui.league.dialog.KlasemenDialog
import com.github.nothing2512.football_v2.ui.league.dialog.LeagueDialog
import com.github.nothing2512.football_v2.ui.team.adapter.TeamAdapter
import com.github.nothing2512.football_v2.ui.team.dialog.TeamDialog
import com.github.nothing2512.football_v2.utils.*
import com.github.nothing2512.football_v2.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class LeagueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeagueBinding

    private val leagueViewModel: LeagueViewModel by viewModel()
    private var leagueId = 0

    private var leagueStatus = false
    private var klasemenStatus = false
    private var teamStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchMain {
            binding = getBinding(R.layout.activity_league)
            subscribeUI()
        }
    }

    private fun subscribeUI() {

        leagueId = intent.getIntExtra(Constants.EXTRA_ID, 0)

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

                    leagueStatus = true

                    val description = it.data?.strDescriptionEN ?: ""
                    val dialog = LeagueDialog(
                        this,
                        description,
                        binding.root as ViewGroup
                    )

                    binding.leagueData = LeagueBinding.parse(
                        it.data,
                        { dialog.show() },
                        { finish() },
                        applicationContext
                    )

                    setLove(it.data)

                    if (isFinishLoading()) stopLoading()
                }
            }
        })

        leagueViewModel.getKlasemen(leagueId).observe(this, Observer {

            when (it.status) {

                Status.LOADING -> startLoading()

                Status.SUCCESS -> {

                    klasemenStatus = true

                    binding.btKlasemen.setOnClickListener { _ -> performKlasemenClick(it.data?.table) }
                    binding.tvKlasement.setOnClickListener { _ -> performKlasemenClick(it.data?.table) }

                    if (isFinishLoading()) stopLoading()
                }

                Status.ERROR -> {

                    Toast.makeText(
                        applicationContext,
                        "Failed to load klasemen",
                        Toast.LENGTH_SHORT
                    ).show()

                    stopLoading()
                }
            }
        })

        leagueViewModel.loadTeams(leagueId).observe(this, Observer {

            when (it.status) {

                Status.LOADING -> startLoading()

                Status.SUCCESS -> {

                    teamStatus = true

                    binding.leagueTeams.apply {
                        layoutManager = GridLayoutManager(
                            this@LeagueActivity,
                            1,
                            RecyclerView.HORIZONTAL,
                            false
                        )
                        adapter = it.data?.teams?.let { teams ->
                            TeamAdapter(
                                teams,
                                true
                            )
                        }
                        clearFocus()
                    }

                    binding.tvMore.setOnClickListener { _ ->
                        it.data?.teams?.let { teams ->
                            TeamDialog(
                                this@LeagueActivity,
                                binding.root as ViewGroup,
                                teams
                            ).show()
                        }
                    }

                    if (isFinishLoading()) stopLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(applicationContext, "Failed to load teams", Toast.LENGTH_SHORT)
                        .show()
                    stopLoading()
                }
            }
        })
    }

    private fun isFinishLoading() = leagueStatus && klasemenStatus && teamStatus

    private fun performKlasemenClick(klasemen: List<KlasemenEntity>?) {
        when {
            klasemen?.get(0)?.idLeague != leagueId -> Toast.makeText(
                applicationContext,
                "Please wait",
                Toast.LENGTH_SHORT
            ).show()
            klasemen.isEmpty() -> Toast.makeText(
                applicationContext,
                "This league doesnt have klasemen",
                Toast.LENGTH_SHORT
            ).show()
            else -> KlasemenDialog(this, binding.root as ViewGroup, klasemen).show()
        }
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

        binding.apply {
            scroll.hide()
            divider.hide()
            tvDescription.hide()
            imContent.hide()
            arrow.hide()
            btKlasemen.hide()
            tvKlasement.hide()
            leagueShimmer.start()
        }
    }

    private fun stopLoading() {

        binding.apply {
            mainFrame.isFocusable = false
            imBackground.requestFocus()
            swipe.isRefreshing = false
            scroll.show()
            divider.show()
            tvDescription.show()
            imContent.show()
            arrow.show()
            tvKlasement.show()
            btKlasemen.show()
            leagueShimmer.stop()
        }
    }
}
