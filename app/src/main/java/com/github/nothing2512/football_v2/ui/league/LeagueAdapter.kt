package com.github.nothing2512.football_v2.ui.league

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.ui.view.league.LeagueItemUI
import com.github.nothing2512.football_v2.utils.bindImage
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.show
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk27.coroutines.onClick

class LeagueAdapter(
    private val data: List<LeagueItemBindingData>? = null,
    private val entities: List<LeagueEntity>? = null
) : RecyclerView.Adapter<LeagueAdapter.MainHolder>() {

    private var size = 0

    init {
        size = when {
            data != null -> data.size
            entities != null -> entities.size
            else -> 1
        }
        if (size == 0) size = 1
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    constructor(data: List<LeagueEntity>) : this(null, data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        MainHolder(LeagueItemUI(), AnkoContext.create(parent.context, parent))

    override fun getItemCount(): Int = size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        when {
            data?.isNotEmpty() == true -> holder.bind(data[position])
            entities?.isNotEmpty() == true -> holder.bind(entities[position])
            else -> holder.bindEmpty()
        }
    }

    class MainHolder(ui: LeagueItemUI, ctx: AnkoContext<ViewGroup>) :
        RecyclerView.ViewHolder(ui.createView(ctx)) {

        private val imLogo: ImageView = itemView.findViewById(Id.imLogo)
        private val tvName: TextView = itemView.findViewById(Id.tvName)
        private val tvDesc: TextView = itemView.findViewById(Id.tvDesc)
        private val tvNotFound: TextView = itemView.findViewById(Id.tvNotFound)

        fun bind(league: LeagueEntity) {
            bind(LeagueItemBindingData.parse(itemView.context, league))
        }

        fun bind(league: LeagueItemBindingData) {
            itemView.onClick { league.onClick.invoke() }
            imLogo.bindImage(league.logo)
            tvName.text = league.name
            tvDesc.text = league.desc
        }

        fun bindEmpty() {
            imLogo.hide()
            tvName.hide()
            tvDesc.hide()
            tvNotFound.show()
        }
    }
}