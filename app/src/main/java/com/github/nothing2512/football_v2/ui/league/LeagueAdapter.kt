package com.github.nothing2512.football_v2.ui.league

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.databinding.LeagueItemBinding
import com.github.nothing2512.football_v2.utils.getBinding

class LeagueAdapter(
    private val data: List<LeagueItemBindingData>? = null,
    private val entities: List<LeagueEntity>? = null
) : RecyclerView.Adapter<LeagueAdapter.MainHolder>() {

    init {

        if (data == null && entities == null)
            throw KotlinNullPointerException("no data found")
    }

    constructor(data: List<LeagueEntity>) : this(null, data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        MainHolder(getBinding(R.layout.league_item, parent))

    override fun getItemCount(): Int = data?.size ?: (entities?.size ?: 0)

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        if (data != null) holder.bind(data[position])
        else if (entities != null) holder.bind(entities[position])
    }

    class MainHolder(private val binding: LeagueItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(league: LeagueItemBindingData) {
            binding.leagueData = league
        }

        fun bind(league: LeagueEntity) {
            binding.leagueData = LeagueItemBindingData.parse(itemView.context, league)
        }
    }
}