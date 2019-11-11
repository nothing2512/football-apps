package com.github.nothing2512.football_v2.ui.event.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.FoulBinding
import com.github.nothing2512.football_v2.databinding.ItemFoulAwayBinding
import com.github.nothing2512.football_v2.databinding.ItemFoulHomeBinding
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.getBinding

class FoulAdapter(
    private val data: List<FoulBinding>,
    private val type: Int
) : RecyclerView.Adapter<FoulAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        if (type == Constants.TYPE_HOME) MainHolder(getBinding(R.layout.item_foul_home, parent))
        else MainHolder(getBinding(R.layout.item_foul_away, parent))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(data[position])
    }

    class MainHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: FoulBinding) {

            if (binding is ItemFoulHomeBinding) binding.fouls = data
            if (binding is ItemFoulAwayBinding) binding.fouls = data
        }
    }
}