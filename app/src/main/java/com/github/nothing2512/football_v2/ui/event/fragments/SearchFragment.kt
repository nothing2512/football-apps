package com.github.nothing2512.football_v2.ui.event.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.ui.event.EventViewModel
import com.github.nothing2512.football_v2.ui.event.adapter.EventAdapter
import com.github.nothing2512.football_v2.ui.view.EventFragmentUI
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.show
import com.github.nothing2512.football_v2.vo.Status
import org.jetbrains.anko.AnkoContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val eventViewModel: EventViewModel by viewModel()

    private var query: String = ""

    private lateinit var eventShimmer: ShimmerFrameLayout
    private lateinit var eventRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ankoContext = context?.let { AnkoContext.create(it, this, false) }
        val v = ankoContext?.let { EventFragmentUI<SearchFragment>().createView(it) }

        v?.findViewById<ShimmerFrameLayout>(R.id.shimmer)?.let { eventShimmer = it }
        v?.findViewById<RecyclerView>(R.id.eventRecyclerView)?.let { eventRecyclerView = it }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchMain {

            eventRecyclerView.apply {
                adapter = EventAdapter()
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            }

            if (query.isNotEmpty()) getData(query)
        }
    }

    fun setQuery(query: String?) {
        try {
            getData(query ?: "")
            this.query = ""
        } catch (e: Exception) {
            this.query = query ?: ""
        }
    }

    private fun getData(query: String) {

        eventViewModel.searchEvent(query).observe(this@SearchFragment, Observer {

            when (it.status) {

                Status.ERROR -> {
                    eventShimmer.hide()
                    eventRecyclerView.show()

                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                Status.SUCCESS -> {
                    eventShimmer.hide()
                    eventRecyclerView.show()

                    it.data?.event?.let { search ->
                        eventRecyclerView.adapter = EventAdapter(search)
                    }
                    eventRecyclerView.clearFocus()
                }

                Status.LOADING -> {

                    eventShimmer.show()
                    eventRecyclerView.hide()
                    eventRecyclerView.adapter = EventAdapter(ArrayList())
                }
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}
