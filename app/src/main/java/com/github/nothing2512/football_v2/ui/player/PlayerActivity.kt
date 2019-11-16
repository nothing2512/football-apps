package com.github.nothing2512.football_v2.ui.player

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.databinding.ActivityPlayerBinding
import com.github.nothing2512.football_v2.ui.league.dialog.LeagueDialog
import com.github.nothing2512.football_v2.utils.*
import com.github.nothing2512.football_v2.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerActivity : AppCompatActivity() {

    private val playerViewModel: PlayerViewModel by viewModel()

    private lateinit var binding: ActivityPlayerBinding
    private var idPlayer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchMain {
            binding = getBinding(R.layout.activity_player)
            idPlayer = intent.getIntExtra(Constants.EXTRA_ID, 0)
            subscribeUI()
        }
    }

    private fun subscribeUI() {

        binding.swipe.setOnRefreshListener { getData() }
        binding.btBack.setOnClickListener { finish() }

        getData()
    }

    private fun getData() {

        startLoading()
        playerViewModel.loadPlayer(idPlayer).observe(this, Observer {

            when (it.status) {

                Status.LOADING -> startLoading()

                Status.SUCCESS -> {
                    binding.player = it.data
                    binding.imContent.setOnClickListener { _ ->
                        LeagueDialog(this, it.data?.strDescriptionEN ?: "", binding.root as ViewGroup).show()
                    }
                    stopLoading()
                }

                Status.ERROR -> {
                    Toast.makeText(applicationContext, "failed to load data", Toast.LENGTH_SHORT)
                        .show()
                    stopLoading()
                }
            }
        })
    }

    private fun startLoading() {
        binding.apply {
            imContent.hide()
            arrow.hide()
            tvDescription.hide()
            divider.hide()
            scroll.hide()
            playerShimmer.start()
        }
    }

    private fun stopLoading() {
        binding.apply {
            imContent.show()
            arrow.show()
            tvDescription.show()
            divider.show()
            scroll.show()
            playerShimmer.stop()
            swipe.isRefreshing = false
        }
    }
}
