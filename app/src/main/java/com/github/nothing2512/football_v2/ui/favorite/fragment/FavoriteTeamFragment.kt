package com.github.nothing2512.football_v2.ui.favorite.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.databinding.FragmentFavoriteTeamBinding
import com.github.nothing2512.football_v2.ui.favorite.FavoriteViewModel
import com.github.nothing2512.football_v2.ui.team.adapter.TeamAdapter
import com.github.nothing2512.football_v2.utils.getBinding
import com.github.nothing2512.football_v2.utils.launchMain
import com.github.nothing2512.football_v2.utils.show
import com.github.nothing2512.football_v2.utils.stop
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTeamFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteTeamBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getBinding(inflater, R.layout.fragment_favorite_team, container)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel.getTeams().observe(this, Observer {
            launchMain {
                binding.favoriteTeamShimmer.stop()
                binding.rvFavoriteTeam.show()
                binding.rvFavoriteTeam.apply {
                    isNestedScrollingEnabled = true
                    layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
                    adapter = TeamAdapter(it)
                    clearFocus()
                }
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = FavoriteTeamFragment()
    }

}
