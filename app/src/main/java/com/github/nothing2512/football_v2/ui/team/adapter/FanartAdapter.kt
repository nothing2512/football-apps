package com.github.nothing2512.football_v2.ui.team.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.databinding.ItemFanartBinding
import com.github.nothing2512.football_v2.utils.bind
import com.github.nothing2512.football_v2.utils.getBinding

class FanartAdapter(private val data: List<String>): RecyclerView.Adapter<FanartAdapter.MainHolder>() {

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainHolder(
            getBinding(
                R.layout.item_fanart,
                parent
            )
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(data[position])
    }

    class MainHolder(private val binding: ItemFanartBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(source: String) {
            binding.imgFanart.bind(source, false)
        }
    }
}