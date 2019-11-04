package com.github.nothing2512.football_v2.ui.view

import androidx.fragment.app.Fragment
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.nothing2512.football_v2.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.include
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

class EventFragmentUI<F: Fragment> : AnkoComponent<F> {

    override fun createView(ui: AnkoContext<F>) = with(ui) {
        constraintLayout {
            include<ShimmerFrameLayout>(R.layout.shimmer)
            recyclerView {
                id = R.id.eventRecyclerView
                isFocusable = false
                isNestedScrollingEnabled = true
            }.lparams(matchParent, matchParent)
        }
    }

}