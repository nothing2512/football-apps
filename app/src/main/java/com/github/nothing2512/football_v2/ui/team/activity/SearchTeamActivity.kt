package com.github.nothing2512.football_v2.ui.team.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.remote.response.TeamResponse
import com.github.nothing2512.football_v2.databinding.ActivitySearchTeamBinding
import com.github.nothing2512.football_v2.ui.team.adapter.TeamAdapter
import com.github.nothing2512.football_v2.ui.team.TeamViewModel
import com.github.nothing2512.football_v2.utils.*
import com.github.nothing2512.football_v2.vo.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchTeamActivity : AppCompatActivity() {

    private val teamViewModel: TeamViewModel by viewModel()
    private lateinit var binding: ActivitySearchTeamBinding

    private val observer = Observer<Resource<TeamResponse>> {
        binding.svTeamList.apply {
            adapter = TeamAdapter(
                it.data?.teams ?: ArrayList()
            )
            clearFocus()
            binding.svTeams.requestFocus()
        }
        stopLoading()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchMain {
            binding = getBinding(R.layout.activity_search_team)
            subscribeUI()
        }
    }

    private fun subscribeUI() {

        binding.svTeamList.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        binding.svTeams.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                startLoading()
                teamViewModel.searchTeam(query ?: "").observe(this@SearchTeamActivity, observer)
                return true
            }

            override fun onQueryTextChange(newText: String?) = true
        })

        binding.svTeams.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus && binding.svTeams.query.isNullOrEmpty() && !binding.svTeams.isVisible)
                binding.svTeams.clearFocus()
            if (!hasFocus) binding.svTeams.isIconified = true
        }
    }

    private fun startLoading() {
        binding.svTeamShimmer.start()
        binding.svTeamList.hide()
    }

    private fun stopLoading() {
        binding.svTeamList.show()
        binding.svTeamShimmer.stop()
    }
}
