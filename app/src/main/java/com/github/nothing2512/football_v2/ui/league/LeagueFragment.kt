package com.github.nothing2512.football_v2.ui.league


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.databinding.FragmentLeagueBinding
import com.github.nothing2512.football_v2.ui.league.adapter.LeagueAdapter
import com.github.nothing2512.football_v2.utils.getBinding
import com.github.nothing2512.football_v2.utils.launchMain
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeagueFragment : Fragment() {

    private val leagueViewModel: LeagueViewModel by viewModel()
    private lateinit var binding: FragmentLeagueBinding
    private lateinit var adapter: LeagueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getBinding(inflater, R.layout.fragment_league, container)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leagueViewModel
            .getLeagues(context)
            .observe(this, Observer {
                launchMain {
                    adapter = LeagueAdapter(it)
                    binding.leagueRecyclerview.apply {
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
