package com.github.nothing2512.football_v2.ui.event.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.ui.event.EventActivity
import com.github.nothing2512.football_v2.ui.view.event.EventItemUI
import com.github.nothing2512.football_v2.utils.bindImage
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.resources.Constants
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.show
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class EventAdapter(private val data: List<EventEntity> = ArrayList()) :
    RecyclerView.Adapter<EventAdapter.MainHolder>() {

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        MainHolder(EventItemUI(), AnkoContext.create(parent.context, parent))

    override fun getItemCount(): Int = if (data.isEmpty()) 1 else data.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        if (data.isNotEmpty()) holder.bind(data[position])
        else holder.bindEmpty()
    }

    class MainHolder(ui: EventItemUI, private val ctx: AnkoContext<ViewGroup>) :
        RecyclerView.ViewHolder(ui.createView(ctx)) {

        private val imThumb: ImageView = itemView.findViewById(Id.imThumb)
        private val tvEvent: TextView = itemView.findViewById(Id.tvSearchEvent)
        private val tvNotFound: TextView = itemView.findViewById(Id.tvNotFound)

        fun bindEmpty() {
            imThumb.hide()
            tvEvent.hide()
            tvNotFound.show()
        }

        fun bind(event: EventEntity) {
            imThumb.bindImage(event.strThumb)
            tvEvent.text = event.strEvent
            tvNotFound.hide()
            itemView.onClick {
                if (event.intHomeScore != null && event.intAwayScore != null)
                    ctx.startActivity<EventActivity>(
                        Constants.EXTRA_ID to event.idEvent
                    )
                else ctx.toast("This event doesn't have detail yet.")
            }
        }
    }

}