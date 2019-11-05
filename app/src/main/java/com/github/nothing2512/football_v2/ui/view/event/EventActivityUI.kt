package com.github.nothing2512.football_v2.ui.view.event

import android.graphics.Typeface
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.lifecycle.LiveData
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.EventEntity
import com.github.nothing2512.football_v2.ui.event.EventActivity
import com.github.nothing2512.football_v2.utils.bindImage
import com.github.nothing2512.football_v2.utils.bindText
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.resources.Strings
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class EventActivityUI(private val event: LiveData<EventEntity>) : AnkoComponent<EventActivity> {

    override fun createView(ui: AnkoContext<EventActivity>) = with(ui) {

        ui.owner.swipe = swipeRefreshLayout {

            id = Id.swipe
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)
            backgroundResource = R.color.content

            nestedScrollView {

                id = Id.scroll

                constraintLayout {

                    imageView {

                        id = Id.imBackground
                        backgroundResource = R.drawable.background_gradient
                        scaleX = -1f
                        translationZ = -1f
                    }.lparams(matchParent, dip(Dimens.MAIN_BACKGROUND_HEIGHT)) {
                        topToTop = PARENT_ID
                    }

                    ui.owner.bar = progressBar {
                        hide()
                    }.lparams(wrapContent, wrapContent) {
                        topToBottom = Id.imBackground
                        startToStart = PARENT_ID
                        endToEnd = PARENT_ID
                    }

                    imageView {

                        id = Id.btBack
                        backgroundResource = R.drawable.left_arrow
                        onClick { ui.owner.onBackPressed() }
                    }.lparams(dip(Dimens.ICON_SIZE), dip(Dimens.ICON_SIZE)) {
                        margin = dip(Dimens.SPACING)
                        startToStart = PARENT_ID
                        topToTop = PARENT_ID
                        margin = dip(Dimens.SPACING)
                    }

                    ui.owner.btLoveEvent = imageView {

                        id = Id.btLoveEvent
                        backgroundResource = R.drawable.love_inactive
                        onClick { ui.owner.onBackPressed() }
                    }.lparams(dip(Dimens.ICON_SIZE), dip(Dimens.ICON_SIZE)) {
                        margin = dip(Dimens.SPACING)
                        endToEnd = PARENT_ID
                        topToTop = PARENT_ID
                    }

                    ui.owner.imThumb = imageView {

                        id = Id.imThumb
                        bindImage(ui.owner, event) { strThumb ?: "" }
                        scaleType = ImageView.ScaleType.FIT_CENTER
                    }.lparams(matchParent, dip(Dimens.EVENT_THUMB_HEIGHT)) {
                        topMargin = dip(Dimens.DUMMY_BOTTOM_SPZCING)
                        topToTop = PARENT_ID
                    }

                    ui.owner.card = cardView {

                        backgroundResource = R.color.content

                        constraintLayout {

                            backgroundResource = R.color.content

                            textView {
                                id = Id.tvEventTitle
                                bindText(ui.owner, event) { strEvent }
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textSize = Dimens.CARD_TEXT_TITLE
                                setTypeface(typeface, Typeface.BOLD)
                                textColorResource = android.R.color.black
                            }.lparams(wrapContent, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToTop = PARENT_ID
                            }

                            textView {
                                id = Id.tvLeague
                                bindText(ui.owner, event) { strLeague }
                                textColorResource = android.R.color.black
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textSize = Dimens.TEXT_ITEM_SIZE
                            }.lparams(wrapContent, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToBottom = Id.tvEventTitle
                            }

                            textView(Strings.TEAM_COLUMN) {
                                id = Id.tvTeamColumn
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                                setTypeface(typeface, Typeface.BOLD)
                            }.lparams(wrapContent, wrapContent) {
                                topMargin = dip(11)
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToBottom = Id.tvLeague
                            }

                            textView {
                                id = Id.tvHomeTeam
                                bindText(ui.owner, event) { strHomeTeam ?: "" }
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                            }.lparams(0, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                rightPadding = dip(Dimens.SPACING)
                                bottomToBottom = Id.tvAwayTeam
                                endToStart = Id.tvTeamColumn
                                startToStart = PARENT_ID
                                topToBottom = Id.tvLeague
                            }

                            textView {
                                id = Id.tvAwayTeam
                                bindText(ui.owner, event) { strAwayTeam ?: "" }
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                            }.lparams(0, wrapContent) {
                                leftPadding = dip(Dimens.SPACING)
                                topMargin = dip(Dimens.SPACING)
                                bottomToBottom = Id.tvHomeTeam
                                endToEnd = PARENT_ID
                                startToEnd = Id.tvTeamColumn
                                topToBottom = Id.tvLeague
                            }

                            textView(Strings.SCORE_COLUMN) {
                                id = Id.tvScoreColumn
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                                setTypeface(typeface, Typeface.BOLD)
                            }.lparams(wrapContent, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToBottom = Id.tvHomeTeam
                            }

                            textView {
                                id = Id.tvHomeScore
                                bindText(ui.owner, event) { intHomeScore.toString() }
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                            }.lparams(0, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                rightPadding = dip(Dimens.SPACING)
                                bottomToBottom = Id.tvAwayScore
                                endToStart = Id.tvScoreColumn
                                startToStart = PARENT_ID
                                topToBottom = Id.tvHomeTeam
                            }

                            textView {
                                id = Id.tvAwayScore
                                bindText(ui.owner, event) { intAwayScore.toString() }
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                            }.lparams(0, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                leftPadding = dip(Dimens.SPACING)
                                bottomToBottom = Id.tvHomeScore
                                endToEnd = PARENT_ID
                                startToEnd = Id.tvScoreColumn
                                topToBottom = Id.tvAwayTeam
                            }

                            constraintLayout {

                                id = Id.foulsContainer

                                textView(Strings.FOUL_COLUMN) {

                                    id = Id.tvFoulColumn
                                    textAlignment = TEXT_ALIGNMENT_CENTER
                                    textColorResource = android.R.color.black
                                    textSize = Dimens.TEXT_ITEM_SIZE
                                    setTypeface(typeface, Typeface.BOLD)
                                }.lparams(wrapContent, wrapContent) {
                                    topMargin = dip(Dimens.SPACING)
                                    endToEnd = PARENT_ID
                                    startToStart = PARENT_ID
                                    topToTop = PARENT_ID
                                }

                                ui.owner.homeFouls = recyclerView {
                                    id = Id.homeFouls
                                }.lparams(0, wrapContent) {
                                    leftPadding = dip(Dimens.SPACING)
                                    endToStart = Id.tvFoulColumn
                                    startToStart = PARENT_ID
                                    topToTop = PARENT_ID
                                }

                                ui.owner.awayFouls = recyclerView {
                                    id = Id.awayFouls
                                }.lparams(0, wrapContent) {
                                    rightPadding = dip(Dimens.SPACING)
                                    startToEnd = Id.tvFoulColumn
                                    endToEnd = PARENT_ID
                                    topToTop = PARENT_ID
                                }

                            }.lparams(matchParent, wrapContent) {
                                topToBottom = Id.tvHomeScore
                            }

                            textView(Strings.ROUND_COLUMN) {
                                id = Id.tvRoundColumn
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                                setTypeface(typeface, Typeface.BOLD)
                            }.lparams(wrapContent, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToBottom = Id.foulsContainer
                            }

                            textView {
                                id = Id.tvRound
                                bindText(ui.owner, event) { intRound.toString() }
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                            }.lparams(0, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                leftPadding = dip(Dimens.SPACING)
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToBottom = Id.tvRoundColumn
                            }

                            textView(Strings.DATE_COLUMN) {
                                id = Id.tvDateColumn
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                                setTypeface(typeface, Typeface.BOLD)
                            }.lparams(wrapContent, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToBottom = Id.tvRound
                            }

                            textView {
                                id = Id.tvDate
                                bindText(ui.owner, event) { dateEvent }
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                            }.lparams(0, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                leftPadding = dip(Dimens.SPACING)
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToBottom = Id.tvDateColumn
                            }

                            textView(Strings.TIME_COLUMN) {
                                id = Id.tvTimeColumn
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                                setTypeface(typeface, Typeface.BOLD)
                            }.lparams(wrapContent, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToBottom = Id.tvDate
                            }

                            textView {
                                id = Id.tvTime
                                bindText(ui.owner, event) { strTime }
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_ITEM_SIZE
                            }.lparams(0, wrapContent) {
                                topMargin = dip(Dimens.SPACING)
                                leftPadding = dip(Dimens.SPACING)
                                endToEnd = PARENT_ID
                                startToStart = PARENT_ID
                                topToBottom = Id.tvTimeColumn
                            }

                        }.lparams(matchParent, wrapContent) {
                            margin = dip(Dimens.SPACING)
                        }
                    }.lparams(matchParent, wrapContent) {
                        margin = dip(Dimens.SPACING)
                        endToEnd = PARENT_ID
                        startToStart = PARENT_ID
                        topToBottom = Id.imThumb
                    }

                }.lparams(matchParent, wrapContent)

            }
        }

        ui.owner.swipe
    }
}