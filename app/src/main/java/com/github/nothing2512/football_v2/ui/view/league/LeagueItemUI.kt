package com.github.nothing2512.football_v2.ui.view.league

import android.graphics.Typeface
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.resources.Strings
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class LeagueItemUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        constraintLayout {

            layoutParams = ViewGroup.LayoutParams(matchParent, dip(120)).apply {
                setPadding(
                    dip(Dimens.SPACING),
                    dip(Dimens.SPACING),
                    dip(Dimens.SPACING),
                    dip(Dimens.SPACING)
                )
            }

            imageView {
                id = Id.imLogo
            }.lparams(
                dip(Dimens.LOGO_ITEM_SIZE), dip(
                    Dimens.LOGO_ITEM_SIZE
                )
            ) {
                bottomToBottom = PARENT_ID
                startToStart = PARENT_ID
                topToTop = PARENT_ID
            }

            textView {
                id = Id.tvName
                textAlignment = TEXT_ALIGNMENT_CENTER
                textColorResource = android.R.color.black
                setTypeface(typeface, Typeface.BOLD)
            }.lparams(0, wrapContent) {
                setMargins(
                    dip(Dimens.SPACING),
                    dip(Dimens.DOUBLE_SPACING),
                    dip(Dimens.SPACING),
                    0
                )
                startToEnd = Id.imLogo
                topToTop = PARENT_ID
            }

            textView {
                id = Id.tvDesc
                maxLines = 2
                textColorResource = R.color.main_grey
            }.lparams(0, wrapContent) {
                setMargins(
                    dip(Dimens.SPACING),
                    dip(Dimens.SPACING),
                    dip(Dimens.SPACING),
                    0
                )
                startToEnd = Id.imLogo
                topToBottom = Id.tvName
            }

            textView(Strings.LEAGUE_NOT_FOUND) {
                id = Id.tvNotFound
                maxLines = 1
                textAlignment = TEXT_ALIGNMENT_CENTER
                textColorResource = R.color.main_grey
                textSize = Dimens.TITLE_DESC_SIZE
                setTypeface(typeface, Typeface.BOLD)
                hide()
            }.lparams(wrapContent, wrapContent) {
                setMargins(0, dip(Dimens.SPACING), 0, 0)
                endToEnd = PARENT_ID
                startToStart = PARENT_ID
                topToTop = PARENT_ID
            }
        }
    }
}