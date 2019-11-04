package com.github.nothing2512.football_v2.ui.view

import androidx.fragment.app.Fragment
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.nothing2512.football_v2.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class LeagueFragmentUI <F: Fragment>: AnkoComponent<F> {

    override fun createView(ui: AnkoContext<F>) = with(ui) {
        constraintLayout {
            backgroundResource = android.R.color.transparent
            include<ShimmerFrameLayout>(R.layout.shimmer)
            recyclerView {
                id = R.id.leagueRecyclerview
                isFocusable = false
                isNestedScrollingEnabled = true
            }.lparams(matchParent, matchParent)
        }
    }

}