package com.github.nothing2512.football_v2.ui.view

import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.ui.favorite.FavoriteActivity
import com.github.nothing2512.football_v2.ui.favorite.FavoriteViewModel
import com.github.nothing2512.football_v2.utils.bindFragment
import com.github.nothing2512.football_v2.utils.resources.Constants
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.resources.Strings
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.nestedScrollView

class FavoriteActivityUI(private val lovedViewModel: FavoriteViewModel) :
    AnkoComponent<FavoriteActivity> {
    override fun createView(ui: AnkoContext<FavoriteActivity>) = with(ui) {

        nestedScrollView {

            setBackgroundResource(R.color.content)
            scrollBarSize = 0

            constraintLayout {

                imageView {

                    id = Id.imBackground
                    isFocusable = true
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    scaleX = -1f
                    setImageResource(R.drawable.background_gradient)
                }.lparams(matchParent, dip(Dimens.MAIN_BACKGROUND_HEIGHT)) {
                    topToTop = PARENT_ID
                }

                imageView {

                    id = Id.btBackHome
                    backgroundResource = R.drawable.left_arrow
                    onClick {
                        ui.owner.onBackPressed()
                    }
                }.lparams(
                    dip(Dimens.ICON_SIZE), dip(
                        Dimens.ICON_SIZE
                    )
                ) {
                    setMargins(
                        dip(Dimens.SPACING),
                        dip(Dimens.SPACING),
                        dip(Dimens.SPACING),
                        dip(Dimens.SPACING)
                    )
                    topToTop = PARENT_ID
                    startToStart = PARENT_ID
                }

                textView(Strings.APP_NAME) {

                    id = Id.tvTitle
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColorResource = R.color.background
                    textSize = Dimens.TITLE_TEXT_SIZE
                    setTypeface(typeface, Typeface.BOLD)
                }.lparams(0, wrapContent) {
                    setMargins(
                        0,
                        dip(Dimens.TITLE_TEXT_SPACING),
                        0,
                        dip(Dimens.DOUBLE_SPACING)
                    )
                    endToEnd = PARENT_ID
                    startToStart = PARENT_ID
                    topToTop = Id.imBackground
                }

                textView(Strings.APP_DESC) {

                    id = Id.tvDesc
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColorResource = R.color.background
                    textSize = Dimens.TITLE_DESC_SIZE
                }.lparams(0, wrapContent) {
                    setMargins(
                        0,
                        dip(Dimens.DOUBLE_SPACING),
                        0,
                        dip(Dimens.DOUBLE_SPACING)
                    )
                    endToEnd = PARENT_ID
                    startToStart = PARENT_ID
                    topToBottom = Id.tvTitle
                }

                imageView {

                    id = Id.imContent
                    backgroundResource = R.drawable.main_content
                }.lparams(matchParent, 0) {
                    bottomToTop = Id.content
                    topToTop = Id.tvContent
                }

                view {

                    id = Id.dummyView
                    translationZ = dip(-2).toFloat()
                }.lparams(matchParent, dip(Dimens.MAIN_DUMMY_HEIGHT)) {
                    topToTop = PARENT_ID
                }

                textView(Strings.LEAGUE) {

                    id = Id.tvLeagueFavorite
                    onClick {
                        lovedViewModel.setFragment(Constants.STATE_LEAGUE)
                    }
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = R.color.main_grey
                    textSize = Dimens.TITLE_DESC_SIZE
                    textColorResource = android.R.color.black
                    setPadding(
                        dip(Dimens.SPACING),
                        dip(Dimens.DOUBLE_SPACING),
                        dip(Dimens.SPACING),
                        0
                    )
                }.lparams(0, matchParent) {
                    setMargins(
                        dip(Dimens.SPACING),
                        dip(Dimens.DOUBLE_SPACING),
                        dip(Dimens.SPACING),
                        0
                    )
                    topToBottom = Id.dummyView
                    startToStart = PARENT_ID
                    endToStart = Id.tvEvent
                }

                textView(Strings.EVENT) {

                    id = Id.tvEvent
                    onClick {
                        lovedViewModel.setFragment(Constants.STATE_EVENT)
                    }
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = R.color.main_grey
                    textSize = Dimens.TITLE_DESC_SIZE
                    textColorResource = android.R.color.black
                    setPadding(
                        dip(Dimens.SPACING),
                        dip(Dimens.DOUBLE_SPACING),
                        dip(Dimens.SPACING),
                        0
                    )
                }.lparams(0, matchParent) {
                    setMargins(
                        dip(Dimens.SPACING),
                        dip(Dimens.DOUBLE_SPACING),
                        dip(Dimens.SPACING),
                        0
                    )
                    topToBottom = Id.dummyView
                    startToEnd = Id.tvLeagueFavorite
                    endToEnd = PARENT_ID
                }

                imageView {

                    id = Id.divider
                    backgroundColor = R.color.main_grey
                }.lparams(matchParent, dip(1)) {
                    setMargins(
                        dip(Dimens.DOUBLE_SPACING),
                        dip(Dimens.SPACING),
                        dip(Dimens.DOUBLE_SPACING),
                        0
                    )
                    topToBottom = Id.tvEvent
                }

                frameLayout {
                    id = Id.content
                    backgroundColor = R.color.content
                    isFocusable = false
                    isNestedScrollingEnabled = false
                    scrollBarSize = 0
                    bindFragment(ui.owner, lovedViewModel.fragment)
                }.lparams(matchParent, wrapContent) {
                    setMargins(
                        dip(Dimens.SPACING),
                        dip(Dimens.SPACING),
                        dip(Dimens.SPACING),
                        dip(Dimens.SPACING)
                    )
                    topToBottom = Id.divider
                }

            }.lparams(matchParent, wrapContent)
        }
    }
}