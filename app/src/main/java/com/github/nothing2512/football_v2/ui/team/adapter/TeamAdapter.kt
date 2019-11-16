package com.github.nothing2512.football_v2.ui.team.adapter

import android.content.Intent
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.TeamEntity
import com.github.nothing2512.football_v2.databinding.ItemTeamBinding
import com.github.nothing2512.football_v2.ui.team.activity.TeamActivity
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.getBinding

class TeamAdapter(
    private val teams: List<TeamEntity>,
    private val isHighlight: Boolean = false
) : RecyclerView.Adapter<TeamAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (teams.isNotEmpty()) MainHolder(
            getBinding(R.layout.item_team, parent)
        )
        else MainHolder(
            getBinding(
                R.layout.not_found,
                parent
            )
        )

    override fun getItemCount() =
        if (teams.isEmpty()) 1 else (if (isHighlight && teams.size > 4) 5 else teams.size)

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        if (teams.isNotEmpty()) holder.bind(teams[position])
    }

    class MainHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(team: TeamEntity) {
            if (binding is ItemTeamBinding) {
                binding.team = team
                val ctx = itemView.context
                itemView.setOnClickListener {
                    ctx.startActivity(Intent(ctx, TeamActivity::class.java).apply {
                        putExtra(Constants.EXTRA_ID, team.idTeam)
                    })
                }
            }
        }
    }
}