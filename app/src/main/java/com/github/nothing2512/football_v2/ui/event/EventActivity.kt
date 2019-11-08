package com.github.nothing2512.football_v2.ui.event

import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.FoulBinding
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.ui.event.adapter.FoulAdapter
import com.github.nothing2512.football_v2.ui.view.event.EventActivityUI
import com.github.nothing2512.football_v2.utils.bindImage
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.resources.Constants
import com.github.nothing2512.football_v2.utils.show
import com.github.nothing2512.football_v2.vo.Status
import org.jetbrains.anko.setContentView
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventActivity : AppCompatActivity() {

    private var awayFoul = ArrayList<FoulBinding>()
    private var homeFoul = ArrayList<FoulBinding>()

    private val eventViewModel: EventViewModel by viewModel()
    private val event = MutableLiveData<EventEntity>()

    lateinit var homeFouls: RecyclerView
    lateinit var awayFouls: RecyclerView
    lateinit var swipe: SwipeRefreshLayout
    lateinit var btLoveEvent: ImageView
    lateinit var imThumb: ImageView
    lateinit var card: CardView
    lateinit var bar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventActivityUI(event).setContentView(this)
        launchMain { subscribeUI() }
    }

    private fun subscribeUI() {

        val eventId = intent.getIntExtra(Constants.EXTRA_ID, 0)

        homeFouls.layoutManager = LinearLayoutManager(this)
        awayFouls.layoutManager = LinearLayoutManager(this)

        swipe.setOnRefreshListener { getData(eventId) }
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

                        homeFoul = setFouls(event.strHomeYellowCards, Constants.YELLOW)
                        homeFoul.addAll(setFouls(event.strHomeRedCards, Constants.RED))
                        awayFoul = setFouls(event.strAwayYellowCards, Constants.YELLOW)
                        awayFoul.addAll(setFouls(event.strAwayRedCards, Constants.RED))

                        if (homeFoul.isNotEmpty()) homeFouls.adapter =
                            FoulAdapter(homeFoul, Constants.TYPE_HOME)

                        if (awayFoul.isNotEmpty()) awayFouls.adapter =
                            FoulAdapter(awayFoul, Constants.TYPE_AWAY)
                    }

                    setLove(it.data)

                    stopLoading()

                    event.postValue(it.data)
                }

            }
        })
    }

    private fun setLove(data: EventEntity?) {

        val love = data?.love == 1

        if (love) btLoveEvent.bindImage(R.drawable.love_active, false)
        else btLoveEvent.bindImage(R.drawable.love_inactive, false)

        btLoveEvent.setOnClickListener {

            if (love) {
                eventViewModel.unlove(data)
                data?.love = 0
            } else {
                eventViewModel.love(data)
                data?.love = 1
            }

            setLove(data)
        }
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
        bar.show()
        card.hide()
    }

    private fun stopLoading() {

        swipe.isRefreshing = false
        awayFouls.isFocusable = false
        homeFouls.isFocusable = false
        imThumb.requestFocus()
        bar.hide()
        card.show()
    }
}
