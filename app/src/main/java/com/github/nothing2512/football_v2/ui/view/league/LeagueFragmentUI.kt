package com.github.nothing2512.football_v2.ui.view.league

import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.fragment.app.Fragment
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.utils.resources.Id
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class LeagueFragmentUI<F : Fragment> : AnkoComponent<F> {

    override fun createView(ui: AnkoContext<F>) = with(ui) {
        constraintLayout {
            backgroundResource = R.color.content
            progressBar {
                id = Id.bar
            }.lparams(wrapContent, wrapContent) {
                topToTop = PARENT_ID
                startToStart = PARENT_ID
                endToEnd = PARENT_ID
            }
            recyclerView {
                id = Id.leagueRecyclerView
                isFocusable = false
                isNestedScrollingEnabled = true
            }.lparams(matchParent, matchParent)
        }
    }

}