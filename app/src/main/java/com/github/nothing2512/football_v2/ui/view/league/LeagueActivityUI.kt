package com.github.nothing2512.football_v2.ui.view.league

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.ui.league.LeagueActivity
import com.github.nothing2512.football_v2.ui.league.LeagueViewModel
import com.github.nothing2512.football_v2.utils.bindFragment
import com.github.nothing2512.football_v2.utils.bindImage
import com.github.nothing2512.football_v2.utils.bindText
import com.github.nothing2512.football_v2.utils.resources.Constants
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.resources.Strings
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LeagueActivityUI(
    private val league: MutableLiveData<LeagueEntity>,
    private val leagueViewModel: LeagueViewModel
) : AnkoComponent<LeagueActivity> {

    override fun createView(ui: AnkoContext<LeagueActivity>) = with(ui) {

        ui.owner.swipe = swipeRefreshLayout {

            id = Id.swipe

            constraintLayout {

                backgroundResource = R.color.background

                ui.owner.scroll = nestedScrollView {

                    id = Id.scroll

                    constraintLayout {

                        ui.owner.imBackground = imageView {
                            id = Id.imBackground
                            scaleType = ImageView.ScaleType.CENTER_CROP
                            scaleX = -1.0f
                            imageResource = R.drawable.background_gradient
                        }.lparams(matchParent, dip(Dimens.MAIN_BACKGROUND_HEIGHT))

                        imageView {
                            id = Id.btBack
                            onClick { ui.owner.onBackPressed() }
                            imageResource = R.drawable.left_arrow
                        }.lparams(
                            dip(Dimens.ICON_SIZE),
                            dip(Dimens.ICON_SIZE)
                        ) {
                            margin = dip(Dimens.SPACING)
                            startToStart = PARENT_ID
                            topToTop = PARENT_ID
                        }

                        ui.owner.btLeagueLoved = imageView {
                            id = Id.btLeagueLoved
                            imageResource = R.drawable.love_inactive

                        }.lparams(
                            dip(Dimens.ICON_SIZE),
                            dip(Dimens.ICON_SIZE)
                        ) {
                            margin = dip(Dimens.SPACING)
                            endToEnd = PARENT_ID
                            topToTop = PARENT_ID
                        }

                        ui.owner.card = cardView {

                            id = Id.card
                            topPadding = dip(Dimens.SPACING)
                            radius = dip(Dimens.SPACING).toFloat()

                            constraintLayout {

                                layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)

                                textView {

                                    id = Id.leagueTitle
                                    textColorResource = android.R.color.black
                                    textSize = Dimens.CARD_TEXT_TITLE
                                    setTypeface(typeface, Typeface.BOLD)
                                    bindText(ui.owner, league) { strLeague }
                                }.lparams(wrapContent, matchParent) {
                                    topMargin = dip(Dimens.SPACING)
                                    endToEnd = PARENT_ID
                                    startToStart = PARENT_ID
                                    topToTop = PARENT_ID
                                }

                                textView {

                                    id = Id.formedYear
                                    textColorResource = R.color.main_grey
                                    textSize = Dimens.CARD_TEXT_YEAR
                                    bindText(ui.owner, league) { intFormedYear.toString() }
                                }.lparams(wrapContent, wrapContent) {
                                    endToEnd = PARENT_ID
                                    startToStart = PARENT_ID
                                    topToBottom = Id.leagueTitle
                                }

                                imageView {

                                    id = Id.imBadge
                                    bindImage(ui.owner, league) { strBadge.toString() }
                                }.lparams(dip(Dimens.BADGE_SIZE), dip(Dimens.BADGE_SIZE)) {
                                    leftMargin = dip(Dimens.DOUBLE_SPACING)
                                    startToStart = PARENT_ID
                                    topToBottom = Id.formedYear
                                }

                                tableLayout {

                                    id = Id.table

                                    tableRow {

                                        textView(Strings.FIRST_EVENT) {
                                            textColorResource = android.R.color.black
                                        }.lparams { rightMargin = dip(Dimens.SPACING) }

                                        textView {
                                            bindText(ui.owner, league) { dateFirstEvent.toString() }
                                            textColorResource = R.color.main_grey
                                        }
                                    }

                                    tableRow {

                                        textView(Strings.GENDER) {
                                            textColorResource = android.R.color.black
                                        }.lparams { rightMargin = dip(Dimens.SPACING) }

                                        textView {
                                            bindText(ui.owner, league) { strGender.toString() }
                                            textColorResource = R.color.main_grey
                                        }
                                    }

                                    tableRow {

                                        textView(Strings.COUNTRY) {
                                            textColorResource = android.R.color.black
                                        }.lparams { rightMargin = dip(Dimens.SPACING) }

                                        textView {
                                            bindText(ui.owner, league) { strCountry.toString() }
                                            textColorResource = R.color.main_grey
                                        }
                                    }
                                }.lparams(wrapContent, wrapContent) {
                                    leftMargin = dip(Dimens.SPACING)
                                    topMargin = dip(Dimens.SPACING)
                                    padding = dip(Dimens.SPACING)
                                    bottomToBottom = Id.imBadge
                                    startToEnd = Id.imBadge
                                    topToTop = Id.imBadge
                                }

                                imageView(R.drawable.domain) {
                                    id = Id.btWebsite
                                    onClick { openBrowser(league) { strWebsite.toString() } }
                                }.lparams(
                                    dip(Dimens.IMAGE_URL_SIZE),
                                    dip(Dimens.IMAGE_URL_SIZE)
                                ) {
                                    margin = dip(Dimens.SPACING)
                                    endToStart = Id.btFacebook
                                    startToStart = PARENT_ID
                                    topToBottom = Id.table
                                }

                                imageView(R.drawable.facebook) {
                                    id = Id.btFacebook
                                    onClick { openBrowser(league) { strFacebook.toString() } }
                                }.lparams(
                                    dip(Dimens.IMAGE_URL_SIZE),
                                    dip(Dimens.IMAGE_URL_SIZE)
                                ) {
                                    margin = dip(Dimens.SPACING)
                                    endToStart = Id.btTwitter
                                    startToEnd = Id.btWebsite
                                    topToBottom = Id.table
                                }

                                imageView(R.drawable.twitter) {
                                    id = Id.btTwitter
                                    onClick { openBrowser(league) { strTwitter.toString() } }
                                }.lparams(
                                    dip(Dimens.IMAGE_URL_SIZE),
                                    dip(Dimens.IMAGE_URL_SIZE)
                                ) {
                                    margin = dip(Dimens.SPACING)
                                    endToStart = Id.btYoutube
                                    startToEnd = Id.btFacebook
                                    topToBottom = Id.table
                                }

                                imageView(R.drawable.youtube) {
                                    id = Id.btYoutube
                                    onClick { openBrowser(league) { strYoutube.toString() } }
                                }.lparams(
                                    dip(Dimens.IMAGE_URL_SIZE),
                                    dip(Dimens.IMAGE_URL_SIZE)
                                ) {
                                    margin = dip(Dimens.SPACING)
                                    endToEnd = PARENT_ID
                                    startToEnd = Id.btTwitter
                                    topToBottom = Id.table
                                }

                            }

                        }.lparams(matchParent, 0) {
                            setMargins(
                                dip(Dimens.CARD_SPACING_SMALL),
                                dip(Dimens.CARD_SPACING_BIG),
                                dip(Dimens.CARD_SPACING_SMALL),
                                dip(Dimens.CARD_SPACING_SMALL)
                            )
                            topToTop = PARENT_ID
                            bottomToBottom = Id.imBackground
                        }

                        view {
                            id = Id.dummyState
                        }.lparams(dip(1), 0) {
                            endToEnd = PARENT_ID
                            startToStart = PARENT_ID
                            topToBottom = Id.imBackground
                        }

                        val btPrevius = imageView {

                            id = Id.btStatePrevius
                            imageResource = R.drawable.state_left_disabled
                        }.lparams(dip(Dimens.STATE_WIDTH), 0) {
                            topMargin = dip(Dimens.SPACING)
                            bottomToBottom = Id.tvStatePrevius
                            endToStart = Id.dummyState
                            topToBottom = Id.imBackground
                        }

                        val btNext = imageView {

                            id = Id.btStateNext
                            imageResource = R.drawable.state_right_enabled
                        }.lparams(dip(Dimens.STATE_WIDTH), 0) {
                            topMargin = dip(Dimens.SPACING)
                            bottomToBottom = Id.tvStateNext
                            startToEnd = Id.dummyState
                            topToBottom = Id.imBackground
                        }

                        btPrevius.onClick {
                            btPrevius.imageResource = R.drawable.state_left_enabled
                            btNext.imageResource = R.drawable.state_right_disabled
                            leagueViewModel.setFragment(ui.owner, Constants.STATE_PREVIUS)
                        }

                        btNext.onClick {
                            btNext.imageResource = R.drawable.state_right_enabled
                            btPrevius.imageResource = R.drawable.state_left_disabled
                            leagueViewModel.setFragment(ui.owner, Constants.STATE_NEXT)
                        }

                        textView(Strings.PREVIUS_EVENT) {

                            id = Id.tvStatePrevius
                            padding = dip(Dimens.SPACING)
                            textColorResource = android.R.color.black
                            textSize = Dimens.STATE_TEXT_SIZE
                        }.lparams(wrapContent, wrapContent) {
                            endToEnd = Id.btStatePrevius
                            startToStart = Id.btStatePrevius
                            topToTop = Id.btStatePrevius
                        }

                        textView(Strings.NEXT_EVENT) {

                            id = Id.tvStateNext
                            padding = dip(Dimens.SPACING)
                            textColorResource = android.R.color.black
                            textSize = Dimens.STATE_TEXT_SIZE
                        }.lparams(wrapContent, wrapContent) {
                            endToEnd = Id.btStateNext
                            startToStart = Id.btStateNext
                            topToTop = Id.btStateNext
                        }

                        ui.owner.mainFrame = frameLayout {

                            id = Id.mainFrame
                            bindFragment(ui.owner, leagueViewModel.fragment)
                        }.lparams(matchParent, wrapContent) {
                            topMargin = dip(Dimens.DOUBLE_SPACING)
                            topToBottom = Id.btStateNext
                        }

                        view().lparams(matchParent, dip(Dimens.DUMMY_BOTTOM_SPZCING)) {
                            topToBottom = Id.mainFrame
                        }

                    }.lparams(matchParent, wrapContent)

                }.lparams(matchParent, matchParent)

                ui.owner.imContent = imageView {

                    id = Id.imContent
                    backgroundResource = R.drawable.main_content
                    onClick {
                        LeagueDialogUI(
                            league
                        ).show(ui)
                    }
                }.lparams(matchParent, 0) {
                    leftMargin = dip(Dimens.DOUBLE_SPACING)
                    rightMargin = dip(Dimens.DOUBLE_SPACING)
                    bottomToBottom = PARENT_ID
                    topToTop = Id.tvDescription
                }

                ui.owner.tvDescription = textView(Strings.DESCRIPTION_SUB_TITLE) {

                    id = Id.tvDescription
                    onClick {
                        LeagueDialogUI(
                            league
                        ).show(ui)
                    }
                    leftPadding = dip(Dimens.SPACING)
                    topPadding = dip(Dimens.DOUBLE_SPACING)
                    rightPadding = dip(Dimens.SPACING)
                    textAlignment = TEXT_ALIGNMENT_CENTER
                    textColorResource = R.color.main_grey
                    textSize = Dimens.TEXT_SIZE
                }.lparams(matchParent, wrapContent) {
                    bottomToTop = Id.divider
                }

                ui.owner.arrow = imageView {

                    id = Id.arrow
                    backgroundResource = R.drawable.arrow_top
                    onClick {
                        LeagueDialogUI(
                            league
                        ).show(ui)
                    }
                }.lparams(dip(Dimens.ICON_SIZE), 0) {
                    topMargin = dip(Dimens.SPACING)
                    rightMargin = dip(Dimens.DOUBLE_SPACING)
                    bottomToBottom = Id.tvDescription
                    endToEnd = Id.tvDescription
                    topToTop = Id.tvDescription
                }

                ui.owner.divider = imageView {

                    id = Id.divider
                    onClick {
                        LeagueDialogUI(
                            league
                        ).show(ui)
                    }
                    backgroundResource = R.color.main_grey
                }.lparams(0, dip(1)) {
                    leftMargin = dip(Dimens.DOUBLE_SPACING)
                    rightMargin = dip(Dimens.DOUBLE_SPACING)
                    bottomMargin = dip(Dimens.SPACING)
                    bottomToBottom = PARENT_ID
                    endToEnd = Id.imContent
                    startToStart = Id.imContent
                }
            }
        }

        ui.owner.swipe
    }

    private fun getLink(url: String?): String {
        val linkReg = Regex("(http|https)://(\\w+)")
        return if (linkReg.containsMatchIn(url ?: "")) "$url"
        else "https://$url"
    }

    private fun <T> AnkoContext<LeagueActivity>.openBrowser(
        data: LiveData<T>,
        url: T.() -> String
    ) {

        data.observe(this.owner, Observer {
            try {
                ctx.startActivity(Intent(Intent.ACTION_VIEW).apply {
                    this.data = Uri.parse(getLink(url.invoke(it)))
                })
            } catch (_: Exception) {
                toast("Url doesnt exist")
            } catch (_: ActivityNotFoundException) {
                toast("Cannot open browser")
            }
        })
    }
}