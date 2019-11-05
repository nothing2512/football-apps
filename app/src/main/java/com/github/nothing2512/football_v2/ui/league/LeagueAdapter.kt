package com.github.nothing2512.football_v2.ui.league

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.ui.view.league.LeagueItemUI
import org.jetbrains.anko.AnkoContext

class LeagueAdapter(
    private val data: List<LeagueItemBindingData>? = null,
    private val entities: List<LeagueEntity>? = null
) : RecyclerView.Adapter<LeagueAdapter.MainHolder>() {

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    constructor(data: List<LeagueEntity>) : this(null, data)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        when {
            data != null -> MainHolder(
                LeagueItemUI(data[viewType]).createView(
                    AnkoContext.create(
                        parent.context,
                        parent
                    )
                )
            )
            entities != null -> MainHolder(
                LeagueItemUI(
                    LeagueItemBindingData.parse(
                        parent.context,
                        entities[viewType]
                    )
                ).createView(AnkoContext.create(parent.context, parent))
            )
            else -> throw KotlinNullPointerException("no data found")
        }

    override fun getItemCount(): Int = data?.size ?: (entities?.size ?: 0)

    override fun onBindViewHolder(holder: MainHolder, position: Int) {}

    override fun getItemViewType(position: Int) = position

    class MainHolder(view: View) : RecyclerView.ViewHolder(view)
}