package com.github.nothing2512.football_v2.ui.view.event

import android.graphics.Typeface
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.lifecycle.MutableLiveData
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.resources.Strings
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class EventItemUI : AnkoComponent<ViewGroup> {

    private val event = MutableLiveData<EventEntity>()

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

            imageView {
                id = Id.imThumb
                scaleType = ImageView.ScaleType.FIT_CENTER
            }.lparams(matchParent, dip(Dimens.EVENT_THUMB_HEIGHT)) {
                topToTop = PARENT_ID
            }

            textView {
                id = Id.tvSearchEvent
                maxLines = 1
                textAlignment = TEXT_ALIGNMENT_CENTER
                textColorResource = android.R.color.black
                textSize = Dimens.TITLE_DESC_SIZE
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
            }.lparams(wrapContent, wrapContent) {
                setMargins(0, dip(Dimens.SPACING), 0, 0)
                endToEnd = PARENT_ID
                startToStart = PARENT_ID
                topToBottom = Id.tvSearchEvent
            }
        }
    }
}