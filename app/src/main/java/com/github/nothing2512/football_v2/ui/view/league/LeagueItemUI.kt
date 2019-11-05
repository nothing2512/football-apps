package com.github.nothing2512.football_v2.ui.view.league

import android.graphics.Typeface
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.utils.bindImage
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

class LeagueItemUI(private val league: LeagueItemBindingData?) : AnkoComponent<ViewGroup> {
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
            onClick { league?.onClick?.invoke() }

            imageView {
                id = Id.imLogo
                bindImage(league?.logo, false)
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
                text = league?.name
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
                text = league?.desc
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
        }
    }
}