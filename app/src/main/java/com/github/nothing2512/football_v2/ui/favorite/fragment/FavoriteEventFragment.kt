package com.github.nothing2512.football_v2.ui.favorite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.ui.event.adapter.EventAdapter
import com.github.nothing2512.football_v2.ui.favorite.FavoriteViewModel
import com.github.nothing2512.football_v2.ui.view.event.EventFragmentUI
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.show
import org.jetbrains.anko.AnkoContext
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteEventFragment : Fragment() {

    private val lovedViewModel: FavoriteViewModel by sharedViewModel()

    private lateinit var bar: ProgressBar
    private lateinit var eventRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ankoContext = context?.let { AnkoContext.create(it, this, false) }
        val v = ankoContext?.let {
            EventFragmentUI<FavoriteEventFragment>()
                .createView(it)
        }

        v?.findViewById<ProgressBar>(Id.bar)?.let { bar = it }
        v?.findViewById<RecyclerView>(Id.eventRecyclerView)?.let { eventRecyclerView = it }

        return v
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        lovedViewModel.getEvents().observe(this, Observer {
            launchMain {
                bar.hide()
                eventRecyclerView.show()
                eventRecyclerView.apply {
                    isNestedScrollingEnabled = true
                    layoutManager = LinearLayoutManager(context)
                    addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
                    adapter = EventAdapter(it)
                    clearFocus()
                }
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = FavoriteEventFragment()
    }
}