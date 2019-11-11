package com.github.nothing2512.football_v2.ui.event.adapter

import android.content.Intent
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.EventBinding
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.databinding.ItemEventBinding
import com.github.nothing2512.football_v2.ui.event.EventActivity
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.getBinding
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.show

class EventAdapter(private val data: List<EventEntity> = ArrayList()) :
    RecyclerView.Adapter<EventAdapter.MainHolder>() {

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        MainHolder(getBinding(R.layout.item_event, parent))

    override fun getItemCount(): Int = if (data.isEmpty()) 1 else data.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        if(data.isEmpty()) holder.bindNull()
        else holder.bind(data[position])
    }

    class MainHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: EventEntity) {
            binding.eventData = EventBinding.parse(
                data,
                {},
                {
                    val context = itemView.context

                    if (isFinishedEvent(data)) context.startActivity(
                        Intent(context, EventActivity::class.java).apply {
                            putExtra(Constants.EXTRA_ID, data.idEvent)
                        })
                    else Toast.makeText(
                        context,
                        "This event doesn't have detail yet.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }

        private fun isFinishedEvent(data: EventEntity): Boolean =
            data.intHomeScore != null && data.intAwayScore != null

        fun bindNull() {
            binding.eventData
            binding.imThumb.hide()
            binding.tvSearchEvent.hide()
            binding.tvNotFound.show()
        }
    }

}