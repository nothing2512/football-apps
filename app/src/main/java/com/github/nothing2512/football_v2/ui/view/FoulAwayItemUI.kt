package com.github.nothing2512.football_v2.ui.view

import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.FoulBinding
import com.github.nothing2512.football_v2.utils.bindImage
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class FoulAwayItemUI(private val foul: FoulBinding) : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        constraintLayout {

            layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent).apply {
                setPadding(dip(11), dip(11), dip(11), dip(11))
            }

            imageView {
                id = R.id.cardType
                bindImage(foul.type, false)
            }.lparams(dip(10), 0) {
                bottomToBottom = R.id.text
                startToStart = PARENT_ID
                topToTop = PARENT_ID
            }

            textView {
                id = R.id.textWatcher
                text = foul.name
                textAlignment = TEXT_ALIGNMENT_CENTER
                textColorResource = android.R.color.black
                textSize = 14f
            }.lparams(0, wrapContent) {
                setMargins(dip(11), 0, 0, 0)
                endToEnd = PARENT_ID
                startToEnd = R.id.cardType
                topToTop = PARENT_ID
            }
        }
    }
}