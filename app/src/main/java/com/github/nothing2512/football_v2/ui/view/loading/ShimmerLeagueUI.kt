package com.github.nothing2512.football_v2.ui.view.loading

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.resources.Strings
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout

class ShimmerLeagueUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        ShimmerFrameLayout(ui.ctx).apply {

            hide()
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)

            addView(
                constraintLayout {

                    layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)

                    imageView {

                        id = Id.imsBackground
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        scaleX = -1f
                        backgroundResource = R.drawable.background_gradient
                    }.lparams(matchParent, dip(Dimens.MAIN_BACKGROUND_HEIGHT)) {
                        topToTop = PARENT_ID
                    }

                    cardView {

                        id = Id.scard
                        backgroundResource = R.color.main_grey

                        constraintLayout {

                            view {

                                id = Id.sTitle
                            }.lparams(
                                dip(Dimens.LEAGUE_TITLE_PLACEHOLDER_WIDTH),
                                dip(Dimens.LEAGUE_TITLE_PLACEHOLDER_HEIGHT)
                            ) {
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToTop = PARENT_ID
                            }

                            view {

                                id = Id.sFormedYear
                            }.lparams(
                                dip(Dimens.LEAGUE_YEAR_PLACEHOLDER_WIDTH),
                                dip(Dimens.LEAGUE_YEAR_PLACEHOLDER_HEIGHT)
                            ) {
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToBottom = PARENT_ID
                                bottomMargin = dip(Dimens.SPACING)
                            }

                            view {

                                id = Id.imsBadge
                                backgroundResource = R.color.main_grey
                            }.lparams(dip(Dimens.BADGE_SIZE), dip(Dimens.BADGE_SIZE)) {
                                leftMargin = dip(Dimens.DOUBLE_SPACING)
                                startToStart = PARENT_ID
                                topToBottom = Id.sFormedYear
                            }

                            tableLayout {

                                id = Id.stable

                                tableRow {

                                    textView(Strings.GENDER) {
                                        textColorResource = android.R.color.black
                                    }.lparams { rightMargin = dip(Dimens.SPACING) }

                                    view {
                                        backgroundColorResource = R.color.main_grey
                                    }.lparams(
                                        dip(Dimens.CONTENT_PLACEHOLDER_WIDTH),
                                        dip(Dimens.CONTENT_PLACEHOLDER_HEIGHT)
                                    )
                                }

                                tableRow {

                                    textView(Strings.COUNTRY) {
                                        textColorResource = android.R.color.black
                                    }.lparams { rightMargin = dip(Dimens.SPACING) }

                                    view {
                                        backgroundColorResource = R.color.main_grey
                                    }.lparams(
                                        dip(Dimens.CONTENT_PLACEHOLDER_WIDTH),
                                        dip(Dimens.CONTENT_PLACEHOLDER_HEIGHT)
                                    )
                                }

                                tableRow {

                                    textView(Strings.FIRST_EVENT) {
                                        textColorResource = android.R.color.black
                                    }.lparams { rightMargin = dip(Dimens.SPACING) }

                                    view {
                                        backgroundColorResource = R.color.main_grey
                                    }.lparams(
                                        dip(Dimens.CONTENT_PLACEHOLDER_WIDTH),
                                        dip(Dimens.CONTENT_PLACEHOLDER_HEIGHT)
                                    )
                                }
                            }.lparams(wrapContent, wrapContent) {
                                leftMargin = dip(Dimens.SPACING)
                                topMargin = dip(Dimens.SPACING)
                                padding = dip(11)
                                bottomToBottom = Id.imsBadge
                                startToEnd = Id.imsBadge
                                topToTop = Id.imsBadge
                            }

                            view {
                                id = Id.btsWebsite
                            }.lparams(
                                dimen(Dimens.IMAGE_URL_SIZE),
                                dimen(Dimens.IMAGE_URL_SIZE)
                            ) {
                                margin = dip(Dimens.SPACING)
                                endToStart = Id.btsFacebook
                                startToStart = PARENT_ID
                                topToBottom = Id.stable
                            }

                            view {
                                id = Id.btsFacebook
                            }.lparams(
                                dimen(Dimens.IMAGE_URL_SIZE),
                                dimen(Dimens.IMAGE_URL_SIZE)
                            ) {
                                margin = dip(Dimens.SPACING)
                                endToStart = Id.btsTwitter
                                startToEnd = Id.btsWebsite
                                topToBottom = Id.stable
                            }

                            view {
                                id = Id.btsTwitter
                            }.lparams(
                                dimen(Dimens.IMAGE_URL_SIZE),
                                dimen(Dimens.IMAGE_URL_SIZE)
                            ) {
                                margin = dip(Dimens.SPACING)
                                endToStart = Id.btsYoutube
                                startToEnd = Id.btsFacebook
                                topToBottom = Id.stable
                            }

                        }.lparams(matchParent, matchParent)

                    }.lparams(matchParent, 0) {
                        topPadding = dip(11)
                        setMargins(
                            dip(Dimens.CARD_SPACING_SMALL),
                            dip(Dimens.CARD_SPACING_BIG),
                            dip(Dimens.CARD_SPACING_SMALL),
                            dip(Dimens.CARD_SPACING_SMALL)
                        )
                    }

                    view {

                        id = Id.sdummyState
                    }.lparams(dip(1), 0) {
                        endToEnd = PARENT_ID
                        startToStart = PARENT_ID
                        topToBottom = Id.imsBackground
                    }

                    imageView {

                        id = Id.btsStatePrevius
                        imageResource = R.drawable.state_left_disabled
                    }.lparams(dip(Dimens.STATE_WIDTH), 0) {
                        topMargin = dip(Dimens.SPACING)
                        bottomToBottom = Id.tvsStatePrevius
                        endToStart = Id.sdummyState
                        topToBottom = Id.imsBackground
                    }

                    imageView {

                        id = Id.btsStateNext
                        imageResource = R.drawable.state_right_enabled
                    }.lparams(dip(Dimens.STATE_WIDTH), 0) {
                        topMargin = dip(Dimens.SPACING)
                        bottomToBottom = Id.tvsStateNext
                        startToEnd = Id.sdummyState
                        topToBottom = Id.imsBackground
                    }

                    textView(Strings.PREVIUS_EVENT) {

                        id = Id.tvsStatePrevius
                        padding = dip(Dimens.SPACING)
                        textColorResource = android.R.color.black
                        textSize = Dimens.STATE_TEXT_SIZE
                    }.lparams(wrapContent, wrapContent) {
                        endToEnd = Id.btsStatePrevius
                        startToStart = Id.btsStatePrevius
                        topToTop = Id.btsStatePrevius
                    }

                    textView(Strings.NEXT_EVENT) {

                        id = Id.tvsStateNext
                        padding = dip(Dimens.SPACING)
                        textColorResource = android.R.color.black
                        textSize = Dimens.STATE_TEXT_SIZE
                    }.lparams(wrapContent, wrapContent) {
                        endToEnd = Id.btsStateNext
                        startToStart = Id.btsStateNext
                        topToTop = Id.btsStateNext
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL

                        ShimmerItemUI().createView(AnkoContext.create(ui.ctx, ui.owner))
                        ShimmerItemUI().createView(AnkoContext.create(ui.ctx, ui.owner))
                        ShimmerItemUI().createView(AnkoContext.create(ui.ctx, ui.owner))

                    }.lparams(matchParent, wrapContent) {
                        topToBottom = Id.tvsStateNext
                    }
                }
            )
        }
    }
}