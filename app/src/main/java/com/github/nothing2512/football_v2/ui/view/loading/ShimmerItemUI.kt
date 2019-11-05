package com.github.nothing2512.football_v2.ui.view.loading

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.core.view.setPadding
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class ShimmerItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        constraintLayout {

            layoutParams =
                ViewGroup.LayoutParams(matchParent, dip(Dimens.EVENT_LIST_ITEM_HEIGHT)).apply {
                    setPadding(dip(Dimens.SPACING))
                }

            view {

                id = Id.imThumbPlaceholder
                backgroundResource = R.color.secondary_grey
            }.lparams(matchParent, dip(Dimens.EVENT_THUMB_HEIGHT)) {
                topToTop = PARENT_ID
            }

            view {

                backgroundResource = R.color.secondary_grey
            }.lparams(matchParent, dip(Dimens.PLACEHOLDER_TEXT_NORMAL)) {
                topMargin = dip(Dimens.SPACING)
                endToEnd = PARENT_ID
                startToStart = PARENT_ID
                topToBottom = Id.imThumbPlaceholder
            }

            view {

                backgroundResource = R.color.secondary_grey
            }.lparams(matchParent, dip(1)) {
                bottomToBottom = PARENT_ID
            }
        }
    }
}