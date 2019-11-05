package com.github.nothing2512.football_v2.ui.view.event

import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.utils.resources.Id
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class EventFragmentUI<F : Fragment> : AnkoComponent<F> {

    override fun createView(ui: AnkoContext<F>) = with(ui) {
        constraintLayout {
            backgroundResource = R.color.content
            progressBar {
                id = Id.bar
            }.lparams(wrapContent, wrapContent) {
                topToTop = ConstraintSet.PARENT_ID
                startToStart = ConstraintSet.PARENT_ID
                endToEnd = ConstraintSet.PARENT_ID
            }
            recyclerView {
                id = Id.eventRecyclerView
                isFocusable = false
                isNestedScrollingEnabled = true
            }.lparams(matchParent, matchParent)
        }
    }

}