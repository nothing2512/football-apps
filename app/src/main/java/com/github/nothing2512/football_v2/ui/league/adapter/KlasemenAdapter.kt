package com.github.nothing2512.football_v2.ui.league.adapter

import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.KlasemenEntity
import com.github.nothing2512.football_v2.databinding.ItemKlasemenBinding
import com.github.nothing2512.football_v2.ui.team.activity.TeamActivity
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.getBinding

class KlasemenAdapter(private val klasemen: List<KlasemenEntity>) :
    RecyclerView.Adapter<KlasemenAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainHolder(getBinding(R.layout.item_klasemen, parent))

    override fun getItemCount() = klasemen.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(klasemen[position])
    }

    class MainHolder(private val binding: ItemKlasemenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(klasemen: KlasemenEntity) {
            binding.klasemen = klasemen
            itemView.setOnClickListener {
                val ctx = itemView.context
                ctx.startActivity(Intent(ctx, TeamActivity::class.java).apply {
                    putExtra(Constants.EXTRA_ID, klasemen.teamid)
                })
            }
        }
    }
}