package com.github.nothing2512.football_v2.ui.event.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.ui.event.EventViewModel
import com.github.nothing2512.football_v2.ui.event.adapter.EventAdapter
import com.github.nothing2512.football_v2.ui.view.event.EventFragmentUI
import com.github.nothing2512.football_v2.utils.*
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.vo.Status
import org.jetbrains.anko.AnkoContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventPreviusFragment(private val idLeague: Int) : Fragment() {

    private val eventViewModel: EventViewModel by viewModel()

    private lateinit var bar: ProgressBar
    private lateinit var eventRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ankoContext = context?.let { AnkoContext.create(it, this, false) }
        val v = ankoContext?.let { EventFragmentUI<EventPreviusFragment>()
            .createView(it) }

        v?.findViewById<ProgressBar>(Id.bar)?.let { bar = it }
        v?.findViewById<RecyclerView>(Id.eventRecyclerView)?.let { eventRecyclerView = it }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        launchMain {

            eventRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            }

            eventViewModel.loadPreviusEvent(idLeague).observe(this@EventPreviusFragment, Observer {

                when (it.status) {

                    Status.SUCCESS -> {

                        stopLoading()
                        it.data?.events?.let { search ->
                            eventRecyclerView.adapter = EventAdapter(search)
                        }
                        eventRecyclerView.clearFocus()
                    }

                    Status.ERROR -> {

                        stopLoading()
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }

                    Status.LOADING -> startLoading()
                }
            })
        }
    }

    private fun startLoading() {

        bar.show()
        eventRecyclerView.hide()
    }

    private fun stopLoading() {

        eventRecyclerView.clearFocus()
        bar.hide()
        eventRecyclerView.show()
    }

    override fun onAttach(context: Context) {
        retainInstance = true
        super.onAttach(context)
    }

    companion object {

        @JvmStatic
        fun newInstance(idLeague: Int) = EventPreviusFragment(idLeague)
    }
}
