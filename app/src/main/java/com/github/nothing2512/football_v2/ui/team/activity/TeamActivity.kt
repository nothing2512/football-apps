package com.github.nothing2512.football_v2.ui.team.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.TeamBinding
import com.github.nothing2512.football_v2.data.source.local.entity.PlayerEntity
import com.github.nothing2512.football_v2.data.source.local.entity.TeamEntity
import com.github.nothing2512.football_v2.databinding.ActivityTeamBinding
import com.github.nothing2512.football_v2.ui.league.dialog.LeagueDialog
import com.github.nothing2512.football_v2.ui.player.PlayerDialog
import com.github.nothing2512.football_v2.ui.team.adapter.FanartAdapter
import com.github.nothing2512.football_v2.ui.team.TeamViewModel
import com.github.nothing2512.football_v2.ui.team.dialog.StadiumDialog
import com.github.nothing2512.football_v2.utils.*
import com.github.nothing2512.football_v2.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamActivity : AppCompatActivity() {

    private val teamViewModel: TeamViewModel by viewModel()

    private lateinit var binding: ActivityTeamBinding
    private var idTeam = 0

    private var isLoadTeam = true
    private var isLoadPlayer = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchMain {
            binding = getBinding(R.layout.activity_team)
            idTeam = intent.getIntExtra(Constants.EXTRA_ID, 0)
            subscribeUI()
        }
    }

    private fun subscribeUI() {

        binding.swipe.setOnRefreshListener { getData() }
        getData()
    }

    private fun getData() {

        startLoading()

        teamViewModel.loadTeam(idTeam).observe(this, Observer {

            when (it.status) {

                Status.LOADING -> startLoading()

                Status.SUCCESS -> {

                    isLoadTeam = false

                    binding.team = it.data
                    binding.teamClick = TeamBinding(
                        { openBrowser(it.data?.strWebsite) },
                        { openBrowser(it.data?.strFacebook) },
                        { openBrowser(it.data?.strTwitter) },
                        { openBrowser(it.data?.strInstagram) },
                        { openBrowser(it.data?.strYoutube) },
                        {
                            LeagueDialog(
                                this,
                                it.data?.strDescriptionEN ?: "",
                                binding.root as ViewGroup
                            ).show()
                        }
                    )

                    binding.fanartList.apply {
                        layoutManager =
                            LinearLayoutManager(this@TeamActivity, RecyclerView.HORIZONTAL, false)
                        adapter = FanartAdapter(
                            listOf(
                                it.data?.strTeamFanart1 ?: "",
                                it.data?.strTeamFanart2 ?: "",
                                it.data?.strTeamFanart3 ?: "",
                                it.data?.strTeamFanart4 ?: ""
                            )
                        )
                    }

                    binding.btStadium.setOnClickListener {  _ ->
                        StadiumDialog(this, binding.root as ViewGroup, it.data).show()
                    }

                    setLove(it.data)

                    if (finishLoad()) stopLoading()
                }

                Status.ERROR -> {

                    Toast.makeText(applicationContext, "Failed to load data", Toast.LENGTH_SHORT)
                        .show()
                    stopLoading()
                }
            }
        })

        teamViewModel.loadPlayers(idTeam).observe(this, Observer {

            when (it.status) {

                Status.LOADING -> startLoading()

                Status.SUCCESS -> {

                    isLoadPlayer = false

                    binding.btPlayer.setOnClickListener { _ ->
                        showPlayers(
                            it.data?.player ?: ArrayList()
                        )
                    }
                    binding.tvPlayer.setOnClickListener { _ ->
                        showPlayers(
                            it.data?.player ?: ArrayList()
                        )
                    }

                    if (finishLoad()) stopLoading()
                }

                Status.ERROR -> {

                    Toast.makeText(applicationContext, "Failed to load data", Toast.LENGTH_SHORT)
                        .show()
                    stopLoading()
                }
            }
        })
    }

    private fun setLove(league: TeamEntity?) {

        val love = league?.love == 1

        if (love) binding.btTeamLove.bind(R.drawable.love_active, false)
        else binding.btTeamLove.bind(R.drawable.love_inactive, false)

        binding.btTeamLove.setOnClickListener {

            if (love) {
                teamViewModel.unlove(league)
                league?.love = 0
            } else {
                teamViewModel.love(league)
                league?.love = 1
            }
            setLove(league)
        }
    }

    private fun showPlayers(data: List<PlayerEntity>) {
        PlayerDialog(this, binding.root as ViewGroup, data).show()
    }

    private fun finishLoad() = !isLoadPlayer && !isLoadTeam

    private fun startLoading() {
        binding.apply {
            teamShimmer.show()
            scroll.hide()
            imContent.hide()
            tvDescription.hide()
            arrow.hide()
            divider.hide()
        }
    }

    private fun stopLoading() {
        binding.apply {
            scroll.show()
            imContent.show()
            tvDescription.show()
            arrow.show()
            divider.show()
            swipe.isRefreshing = false
            teamShimmer.stop()
        }
    }

    private fun getLink(url: String?): String {
        val linkReg = Regex("(http|https)://(\\w+)")
        return if (linkReg.containsMatchIn(url ?: "")) "$url"
        else "https://$url"
    }

    private fun openBrowser(url: String?) {

        try {
            startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(getLink(url)) })
        } catch (_: Exception) {
            Toast.makeText(applicationContext, "Url doesnt exist", Toast.LENGTH_SHORT).show()
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(applicationContext, "Cannot open browser", Toast.LENGTH_SHORT).show()
        }
    }
}
