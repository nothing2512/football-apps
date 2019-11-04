package com.github.nothing2512.football_v2.ui.view

import android.graphics.Typeface
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.LeagueItemBindingData
import com.github.nothing2512.football_v2.utils.bindImage
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

class LeagueItemUI(private val league: LeagueItemBindingData?) : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        constraintLayout {

            layoutParams = ViewGroup.LayoutParams(matchParent, dip(120)).apply {
                setPadding(dip(11), dip(11), dip(11), dip(11))
            }
            onClick { league?.onClick?.invoke() }

            imageView {
                id = R.id.imLogo
                bindImage(league?.logo, false)
            }.lparams(dip(90), dip(90)) {
                bottomToBottom = PARENT_ID
                startToStart = PARENT_ID
                topToTop = PARENT_ID
            }

            textView {
                id = R.id.tvName
                text = league?.name
                textAlignment = TEXT_ALIGNMENT_CENTER
                textColorResource = android.R.color.black
                setTypeface(typeface, Typeface.BOLD)
            }.lparams(0, wrapContent) {
                setMargins(dip(11), dip(22), dip(11), 0)
                startToEnd = R.id.imLogo
                topToTop = PARENT_ID
            }

            textView {
                id = R.id.tvDesc
                maxLines = 2
                text = league?.desc
                textColorResource = R.color.main_grey
            }.lparams(0, wrapContent) {
                setMargins(dip(11), dip(11), dip(11), 0)
                startToEnd = R.id.imLogo
                topToBottom = R.id.tvName
            }
        }
    }
}