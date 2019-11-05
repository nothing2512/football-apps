package com.github.nothing2512.football_v2.ui.view.event

import android.graphics.Typeface
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.ui.event.EventActivity
import com.github.nothing2512.football_v2.utils.resources.Constants
import com.github.nothing2512.football_v2.utils.bindImage
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.resources.Strings
import com.github.nothing2512.football_v2.utils.show
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

class EventItemUI(
    private val event: EventEntity?,
    private val empty: Boolean = false
) : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        constraintLayout {

            layoutParams = ViewGroup.LayoutParams(matchParent, dip(220)).apply {
                setPadding(
                    dip(Dimens.SPACING),
                    dip(Dimens.SPACING),
                    dip(Dimens.SPACING),
                    dip(Dimens.SPACING)
                )
            }

            onClick {
                if (event?.intHomeScore != null && event.intAwayScore != null)
                    startActivity<EventActivity>(
                        Constants.EXTRA_ID to event.idEvent
                    )
                else toast("This event doesn't have detail yet.")
            }

            imageView {
                id = Id.imThumb
                bindImage(event?.strThumb, false)
                scaleType = ImageView.ScaleType.FIT_CENTER
                if(empty) hide()
            }.lparams(matchParent, dip(Dimens.EVENT_THUMB_HEIGHT)) {
                topToTop = PARENT_ID
            }

            textView {
                id = Id.tvSearchEvent
                maxLines = 1
                text = event?.strEvent
                textAlignment = TEXT_ALIGNMENT_CENTER
                textColorResource = android.R.color.black
                textSize = Dimens.TITLE_DESC_SIZE
                if(empty) hide()
            }.lparams(wrapContent, wrapContent) {
                setMargins(0, dip(Dimens.SPACING), 0, 0)
                endToEnd = PARENT_ID
                startToStart = PARENT_ID
                topToBottom = Id.imThumb
            }

            textView(Strings.EVENT_NOT_FOUND) {
                id = Id.tvNotFound
                maxLines = 1
                textAlignment = TEXT_ALIGNMENT_CENTER
                textColorResource = R.color.main_grey
                textSize = Dimens.TITLE_DESC_SIZE
                setTypeface(typeface, Typeface.BOLD)
                if(empty) show() else hide()
            }.lparams(wrapContent, wrapContent) {
                setMargins(0, dip(Dimens.SPACING), 0, 0)
                endToEnd = PARENT_ID
                startToStart = PARENT_ID
                topToBottom = Id.tvSearchEvent
            }
        }
    }
}