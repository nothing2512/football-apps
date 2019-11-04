package com.github.nothing2512.football_v2.ui.event

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.EventBinding
import com.github.nothing2512.football_v2.binding.FoulBinding
import com.github.nothing2512.football_v2.databinding.ActivityEventBinding
import com.github.nothing2512.football_v2.ui.event.adapter.FoulAdapter
import com.github.nothing2512.football_v2.utils.*
import com.github.nothing2512.football_v2.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding

    private var awayFoul = ArrayList<FoulBinding>()
    private var homeFoul = ArrayList<FoulBinding>()

    private val eventViewModel: EventViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launchMain {

            binding = getBinding(R.layout.activity_event)
            subscribeUI()
        }
    }

    private fun subscribeUI() {

        val eventId = intent.getIntExtra(Constants.EXTRA_ID, 0)

        binding.homeFouls.layoutManager = LinearLayoutManager(this)
        binding.awayFouls.layoutManager = LinearLayoutManager(this)

        binding.swipe.setOnRefreshListener { getData(eventId) }
        getData(eventId)
    }

    private fun getData(eventId: Int) {
        eventViewModel.getDetail(eventId).observe(this, Observer {

            when (it.status) {

                Status.LOADING -> startLoading()

                Status.ERROR -> {

                    stopLoading()
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }

                Status.SUCCESS -> {

                    it.data?.let { event ->
                        binding.eventData = EventBinding.parse(
                            event,
                            { finish() },
                            {}
                        )

                        homeFoul = setFouls(event.strHomeYellowCards, Color.YELLOW)
                        homeFoul.addAll(setFouls(event.strHomeRedCards, Color.RED))
                        awayFoul = setFouls(event.strAwayYellowCards, Color.YELLOW)
                        awayFoul.addAll(setFouls(event.strAwayRedCards, Color.RED))

                        binding.homeFouls.adapter = FoulAdapter(homeFoul, Constants.TYPE_HOME)
                        binding.awayFouls.adapter = FoulAdapter(awayFoul, Constants.TYPE_AWAY)
                    }

                    it.data?.love?.let { love ->

                        if (love) binding.btLoveEvent.bind(R.drawable.love_active, false)
                        else binding.btLoveEvent.bind(R.drawable.love_inactive, false)

                        binding.btLoveEvent.setOnClickListener {

                            if (love) eventViewModel.unlove(eventId)
                            else eventViewModel.love(eventId)
                        }
                    }

                    stopLoading()
                }

            }
        })
    }

    private fun setFouls(fouls: String?, @ColorInt color: Int) =
        ArrayList<FoulBinding>().apply {
            if (!fouls.isNullOrEmpty()) fouls.split(";").let { names ->
                names.forEach { name ->
                    if (name.isNotEmpty()) add(FoulBinding(name.trim(), color))
                }
            }
        }

    private fun startLoading() {

        binding.scroll.hide()
        binding.shimmer.show()
    }

    private fun stopLoading() {

        binding.swipe.isRefreshing = false
        binding.scroll.show()
        binding.shimmer.stop()
        binding.awayFouls.isFocusable = false
        binding.homeFouls.isFocusable = false
        binding.imThumb.requestFocus()
    }
}
