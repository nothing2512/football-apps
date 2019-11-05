package com.github.nothing2512.football_v2.ui.view.league

import android.content.DialogInterface
import android.view.View.TEXT_ALIGNMENT_CENTER
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.lifecycle.LiveData
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity
import com.github.nothing2512.football_v2.ui.league.LeagueActivity
import com.github.nothing2512.football_v2.utils.bindText
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.resources.Strings
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LeagueDialogUI(private val league: LiveData<LeagueEntity>) {

    private lateinit var dialog: DialogInterface

    fun show(ui: AnkoContext<LeagueActivity>) = with(ui) {
        dialog = alert {

            customView {

                swipeRefreshLayout {

                    setColorSchemeResources(android.R.color.transparent)
                    setOnRefreshListener {
                        isRefreshing = false
                        dialog.dismiss()
                    }

                    scrollView {

                        scrollBarSize = 0

                        constraintLayout {

                            imageView {

                                id = Id.imContent
                                backgroundResource = R.color.content
                            }.lparams(matchParent, 0) {
                                bottomToBottom = PARENT_ID
                                topToTop = PARENT_ID
                            }

                            textView(Strings.DESCRIPTION_SUB_TITLE) {

                                id = Id.tvContent
                                textColor = R.color.main_grey
                                textSize = Dimens.TEXT_SIZE
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                onClick { dialog.dismiss() }
                            }.lparams(matchParent, wrapContent) {
                                leftMargin = dip(Dimens.SPACING)
                                topMargin = dip(Dimens.DOUBLE_SPACING)
                                rightMargin = dip(Dimens.SPACING)
                                topToTop = Id.imContent
                            }

                            imageView {

                                backgroundResource = R.drawable.arrow_down
                                onClick { dialog.dismiss() }
                            }.lparams(
                                dip(Dimens.ICON_SIZE), dip(
                                    Dimens.ICON_SIZE
                                )
                            ) {
                                setMargins(
                                    0, dip(Dimens.SPACING), dip(
                                        Dimens.DOUBLE_SPACING
                                    ), 0
                                )
                                bottomToBottom = Id.tvContent
                                endToEnd = Id.tvContent
                                topToTop = Id.tvContent
                            }

                            imageView {

                                id = Id.divider
                                backgroundResource = R.color.main_grey
                                onClick { dialog.dismiss() }
                            }.lparams(matchParent, dip(1)) {
                                setMargins(
                                    dip(Dimens.DOUBLE_SPACING), dip(
                                        Dimens.SPACING
                                    ), dip(
                                        Dimens.DOUBLE_SPACING
                                    ), 0
                                )
                                topToBottom = Id.tvContent
                            }

                            textView {

                                bindText(ui.owner, league) { strDescriptionEN.toString() }
                                textAlignment = TEXT_ALIGNMENT_CENTER
                                textColorResource = android.R.color.black
                                textSize = Dimens.TEXT_SIZE
                            }.lparams(matchParent, wrapContent) {
                                setMargins(0, dip(Dimens.DOUBLE_SPACING), 0, 0)
                                margin = dip(Dimens.SPACING)
                                topToBottom = Id.divider
                            }
                        }
                    }
                }
            }
        }.show()

        dialog
    }
}