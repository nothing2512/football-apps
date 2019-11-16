package com.github.nothing2512.football_v2.ui.player

import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.PlayerEntity
import com.github.nothing2512.football_v2.databinding.ItemPlayerBinding
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.getBinding

class PlayerAdapter (private val players: List<PlayerEntity>): RecyclerView.Adapter<PlayerAdapter.MainHolder>() {

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainHolder(getBinding(R.layout.item_player, parent))

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(players[position])
    }


    class MainHolder(private val binding: ItemPlayerBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(player: PlayerEntity) {
            binding.player = player
            val ctx = itemView.context
            itemView.setOnClickListener {
                ctx.startActivity(Intent(ctx, PlayerActivity::class.java).apply {
                    putExtra(Constants.EXTRA_ID, player.idPlayer)
                })
            }
        }
    }
}