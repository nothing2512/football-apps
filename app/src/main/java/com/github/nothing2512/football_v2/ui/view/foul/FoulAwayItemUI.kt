package com.github.nothing2512.football_v2.ui.view.foul

import android.graphics.Color
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.binding.FoulBinding
import com.github.nothing2512.football_v2.utils.resources.Constants
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class FoulAwayItemUI(private val foul: FoulBinding) : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        constraintLayout {

            layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent).apply {
                setPadding(
                    dip(Dimens.SPACING), dip(
                        Dimens.SPACING
                    ), dip(
                        Dimens.SPACING
                    ), dip(
                        Dimens.SPACING
                    )
                )
            }

            textView {
                id = Id.textWatcher
                text = foul.name
                textAlignment = TEXT_ALIGNMENT_CENTER
                textColorResource = if (foul.type == Constants.YELLOW) R.color.main_yellow else R.color.main_red
                textSize = Dimens.FOULS_TEXT_ITEM_SIZE
            }.lparams(0, wrapContent) {
                setMargins(dip(Dimens.SPACING), 0, 0, 0)
                endToEnd = PARENT_ID
                startToStart = PARENT_ID
                topToTop = PARENT_ID
            }
        }
    }
}