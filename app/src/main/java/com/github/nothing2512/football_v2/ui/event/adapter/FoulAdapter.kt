package com.github.nothing2512.football_v2.ui.event.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.nothing2512.football_v2.binding.FoulBinding
import com.github.nothing2512.football_v2.ui.view.FoulAwayItemUI
import com.github.nothing2512.football_v2.ui.view.FoulHomeItemUI
import com.github.nothing2512.football_v2.utils.Constants
import org.jetbrains.anko.AnkoContext

class FoulAdapter(
    private val data: List<FoulBinding>,
    private val type: Int
) : RecyclerView.Adapter<FoulAdapter.MainHolder>() {

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        if (type == Constants.TYPE_HOME)
            MainHolder(
                FoulHomeItemUI(data[viewType]).createView(
                    AnkoContext.create(
                        parent.context,
                        parent
                    )
                )
            )
        else
            MainHolder(
                FoulAwayItemUI(data[viewType]).createView(
                    AnkoContext.create(
                        parent.context,
                        parent
                    )
                )
            )

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: MainHolder, position: Int) {}

    class MainHolder(view: View) : RecyclerView.ViewHolder(view)
}