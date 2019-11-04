package com.github.nothing2512.football_v2.ui.loved.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.ui.event.adapter.EventAdapter
import com.github.nothing2512.football_v2.ui.loved.LovedViewModel
import com.github.nothing2512.football_v2.ui.view.EventFragmentUI
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.show
import com.github.nothing2512.football_v2.utils.stop
import org.jetbrains.anko.AnkoContext
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LovedEventFragment : Fragment() {

    private val lovedViewModel: LovedViewModel by sharedViewModel()

    private lateinit var eventShimmer: ShimmerFrameLayout
    private lateinit var eventRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ankoContext = context?.let { AnkoContext.create(it, this, false) }
        val v = ankoContext?.let { EventFragmentUI<LovedEventFragment>().createView(it) }

        v?.findViewById<ShimmerFrameLayout>(R.id.shimmer)?.let { eventShimmer = it }
        v?.findViewById<RecyclerView>(R.id.eventRecyclerView)?.let { eventRecyclerView = it }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lovedViewModel.getEvents().observe(this, Observer {
            launchMain {
                eventShimmer.stop()
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
        fun newInstance() = LovedEventFragment()
    }
}