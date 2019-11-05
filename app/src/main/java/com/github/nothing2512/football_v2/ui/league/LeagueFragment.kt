package com.github.nothing2512.football_v2.ui.league


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
import com.github.nothing2512.football_v2.ui.view.league.LeagueFragmentUI
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.resources.Id
import org.jetbrains.anko.AnkoContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeagueFragment : Fragment() {

    private val leagueViewModel: LeagueViewModel by viewModel()

    private lateinit var bar: ProgressBar
    private lateinit var leagueRecyclerview: RecyclerView
    private lateinit var adapter: LeagueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ankoContext = context?.let { AnkoContext.create(it, this, false) }
        val v = ankoContext?.let {
            LeagueFragmentUI<LeagueFragment>()
                .createView(it)
        }

        v?.findViewById<ProgressBar>(Id.bar)?.let { bar = it }
        v?.findViewById<RecyclerView>(Id.leagueRecyclerView)?.let { leagueRecyclerview = it }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leagueViewModel
            .getLeagues(context)
            .observe(this, Observer {
                launchMain {
                    bar.hide()
                    adapter = LeagueAdapter(it)
                    leagueRecyclerview.apply {
                        isNestedScrollingEnabled = true
                        layoutManager = LinearLayoutManager(context)
                        addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
                        adapter = this@LeagueFragment.adapter
                        clearFocus()
                    }
                }
            })
    }

    companion object {

        @JvmStatic
        fun newInstance() = LeagueFragment()
    }
}
