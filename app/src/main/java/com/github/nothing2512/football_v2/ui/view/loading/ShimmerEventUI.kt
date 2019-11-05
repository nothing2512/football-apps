package com.github.nothing2512.football_v2.ui.view.loading

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.ui.event.EventActivity
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

class ShimmerEventUI : AnkoComponent<EventActivity> {
    override fun createView(ui: AnkoContext<EventActivity>)= with(ui) {
        ShimmerFrameLayout(ui.ctx).apply {

            hide()
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
            backgroundResource = R.color.background

            addView(
                constraintLayout {

                    layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
                    backgroundResource = R.color.background

                    imageView {
                        backgroundResource = R.drawable.background_gradient
                        scaleX = -1f
                        translationZ -1f
                    }.lparams(matchParent, dip(Dimens.MAIN_BACKGROUND_HEIGHT)) {
                        topToTop = PARENT_ID
                    }

                    imageView {
                        onClick { ui.owner.onBackPressed() }
                        backgroundResource = R.drawable.left_arrow
                    }.lparams(dip(Dimens.ICON_SIZE), dip(Dimens.ICON_SIZE)) {
                        padding = dip(Dimens.SPACING)
                        startToStart = PARENT_ID
                        topToTop = PARENT_ID
                    }

                    view {
                        id = Id.dummyThumb
                        backgroundResource = R.color.main_grey
                    }.lparams(matchParent, dip(Dimens.EVENT_THUMB_HEIGHT)) {
                        setMargins(
                            dip(Dimens.CARD_SPACING_SMALL),
                            dip(Dimens.CARD_SPACING_BIG),
                            dip(Dimens.CARD_SPACING_SMALL),
                            0
                        )
                    }

                    cardView {
                        backgroundResource = R.color.main_grey
                    }.lparams(matchParent, 0) {
                        margin = dip(Dimens.SPACING)
                        bottomToBottom = PARENT_ID
                        endToEnd = PARENT_ID
                        startToStart = PARENT_ID
                        bottomToBottom = PARENT_ID
                    }
                }
            )
        }
    }
}