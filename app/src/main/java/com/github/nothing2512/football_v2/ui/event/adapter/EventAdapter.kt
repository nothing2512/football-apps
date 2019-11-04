package com.github.nothing2512.football_v2.ui.event.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.ui.view.EventItemUI
import org.jetbrains.anko.AnkoContext

class EventAdapter(private val data: List<EventEntity> = ArrayList()) :
    RecyclerView.Adapter<EventAdapter.MainHolder>() {

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        MainHolder(
            EventItemUI(
                data[viewType],
                data.isEmpty()
            ).createView(AnkoContext.create(parent.context, parent))
        )

    override fun getItemCount(): Int = if (data.isEmpty()) 1 else data.size

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: MainHolder, position: Int) {}

    class MainHolder(view: View) : RecyclerView.ViewHolder(view)

}