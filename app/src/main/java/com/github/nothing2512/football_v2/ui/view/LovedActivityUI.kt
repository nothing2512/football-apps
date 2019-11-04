package com.github.nothing2512.football_v2.ui.view

import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.ui.loved.LovedActivity
import com.github.nothing2512.football_v2.ui.loved.LovedViewModel
import com.github.nothing2512.football_v2.utils.Constants
import com.github.nothing2512.football_v2.utils.bindFragment
import com.github.nothing2512.football_v2.utils.hide
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.nestedScrollView

class LovedActivityUI(private val lovedViewModel: LovedViewModel) : AnkoComponent<LovedActivity> {
    override fun createView(ui: AnkoContext<LovedActivity>) = with(ui) {

        nestedScrollView {

            setBackgroundResource(R.color.content)
            scrollBarSize = 0

            constraintLayout {

                imageView {

                    id = R.id.imBackground
                    isFocusable = true
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    scaleX = -1f
                    setImageResource(R.drawable.background_gradient)
                }.lparams(matchParent, dip(300)) {
                    topToTop = PARENT_ID
                }

                imageView {

                    id = R.id.btBackHome
                    backgroundResource = R.drawable.left_arrow
                    hide()
                    onClick {
                        ui.owner.onBackPressed()
                    }
                }.lparams(dip(37), dip(37)) {
                    setMargins(dip(11), dip(11), dip(11), dip(11))
                    topToTop = PARENT_ID
                    startToStart = PARENT_ID
                }

                imageView {

                    id = R.id.btHomeLove
                    backgroundResource = R.drawable.love_active
                    onClick {
                        startActivity<LovedActivity>()
                    }
                }.lparams(dip(37), dip(37)) {
                    setMargins(dip(11), dip(11), dip(11), dip(11))
                    endToEnd = PARENT_ID
                    topToTop = PARENT_ID
                }

                textView {

                    id = R.id.tvTitle
                    textResource = R.string.app_name
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColorResource = R.color.background
                    textSize = 32f
                    setTypeface(typeface, Typeface.BOLD)
                }.lparams(0, wrapContent) {
                    setMargins(0, dip(66), 0, dip(22))
                    endToEnd = PARENT_ID
                    startToStart = PARENT_ID
                    topToTop = R.id.imBackground
                }

                textView {

                    id = R.id.tvDesc
                    textResource = R.string.app_desc
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColorResource = R.color.background
                    textSize = 22f
                }.lparams(0, wrapContent) {
                    setMargins(0, dip(22), 0, dip(22))
                    endToEnd = PARENT_ID
                    startToStart = PARENT_ID
                    topToBottom = R.id.tvTitle
                }

                imageView {

                    id = R.id.imContent
                    backgroundResource = R.drawable.main_content
                }.lparams(matchParent, 0) {
                    bottomToTop = R.id.content
                    topToTop = R.id.tvContent
                }

                view {

                    id = R.id.dummyView
                    translationZ = dip(-2).toFloat()
                }.lparams(matchParent, dip(256)) {
                    topToTop = PARENT_ID
                }

                textView {

                    id = R.id.tvLeagueLoved
                    onClick {
                        lovedViewModel.setFragment(Constants.STATE_LEAGUE)
                    }
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = R.color.main_grey
                    textSize = 22f
                    textResource = R.string.league
                    textColorResource = android.R.color.black
                    setPadding(dip(11), dip(22), dip(11), 0)
                }.lparams(0, matchParent) {
                    setMargins(dip(11), dip(22), dip(11), 0)
                    topToBottom = R.id.dummyView
                    startToStart = PARENT_ID
                    endToStart = R.id.tvEvent
                }

                textView {

                    id = R.id.tvEvent
                    onClick {
                        lovedViewModel.setFragment(Constants.STATE_EVENT)
                    }
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = R.color.main_grey
                    textSize = 22f
                    textResource = R.string.league
                    textColorResource = android.R.color.black
                    setPadding(dip(11), dip(22), dip(11), 0)
                }.lparams(0, matchParent) {
                    setMargins(dip(11), dip(22), dip(11), 0)
                    topToBottom = R.id.dummyView
                    startToEnd = R.id.tvLeagueLoved
                    endToEnd = PARENT_ID
                }

                imageView {

                    id = R.id.divider
                    backgroundColor = R.color.main_grey
                }.lparams(matchParent, dip(1)) {
                    setMargins(dip(22), dip(11), dip(22), 0)
                    topToBottom = R.id.tvEvent
                }

                frameLayout {
                    id = R.id.content
                    backgroundColor = R.color.content
                    isFocusable = false
                    isNestedScrollingEnabled = false
                    scrollBarSize = 0
                    bindFragment(ui.owner, lovedViewModel.fragment)
                }.lparams(matchParent, wrapContent) {
                    setMargins(dip(11), dip(11), dip(11), dip(11))
                    topToBottom = R.id.divider
                }

            }.lparams(matchParent, wrapContent)
        }
    }
}