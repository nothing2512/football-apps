package com.github.nothing2512.football_v2.ui.view

import android.graphics.Typeface.BOLD
import android.view.Gravity
import android.view.View.LAYOUT_DIRECTION_RTL
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import com.github.nothing2512.football_v2.R
import com.github.nothing2512.football_v2.ui.favorite.FavoriteActivity
import com.github.nothing2512.football_v2.ui.home.HomeActivity
import com.github.nothing2512.football_v2.ui.home.HomeViewModel
import com.github.nothing2512.football_v2.utils.bindFragment
import com.github.nothing2512.football_v2.utils.bindText
import com.github.nothing2512.football_v2.utils.hide
import com.github.nothing2512.football_v2.utils.resources.Dimens
import com.github.nothing2512.football_v2.utils.resources.Id
import com.github.nothing2512.football_v2.utils.resources.Strings
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class HomeActivityUI(private val homeViewModel: HomeViewModel) : AnkoComponent<HomeActivity> {
    override fun createView(ui: AnkoContext<HomeActivity>) = with(ui) {

        swipeRefreshLayout {

            id = Id.swipe

            nestedScrollView {

                setBackgroundResource(R.color.content)
                scrollBarSize = 0

                constraintLayout {

                    ui.owner.imBackground = imageView {

                        id = Id.imBackground
                        isFocusable = true
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        scaleX = -1f
                        setImageResource(R.drawable.background_gradient)
                    }.lparams(matchParent, dip(Dimens.MAIN_BACKGROUND_HEIGHT)) {
                        topToTop = PARENT_ID
                    }

                    ui.owner.btBackHome = imageView {

                        id = Id.btBackHome
                        backgroundResource = R.drawable.left_arrow
                        hide()
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

                    imageView {

                        id = Id.btHomeLove
                        backgroundResource = R.drawable.love_active
                        onClick {
                            startActivity<FavoriteActivity>()
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
                        endToEnd = PARENT_ID
                        topToTop = PARENT_ID
                    }

                    textView(Strings.APP_NAME) {

                        id = Id.tvTitle
                        textAlignment = TEXT_ALIGNMENT_CENTER
                        textColorResource = R.color.background
                        textSize = Dimens.TITLE_TEXT_SIZE
                        setTypeface(typeface, BOLD)
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
                        textAlignment = TEXT_ALIGNMENT_CENTER
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

                    ui.owner.searchView = searchView {

                        id = Id.searchView
                        gravity = Gravity.END
                        layoutDirection = LAYOUT_DIRECTION_RTL
                        queryHint = Strings.SEARCH_MATCH
                    }.lparams(matchParent, wrapContent) {
                        setMargins(
                            0,
                            dip(Dimens.SPACING),
                            dip(Dimens.SPACING),
                            dip(Dimens.SPACING)
                        )
                        bottomToTop = Id.imContent
                    }

                    view {

                        id = Id.dummyView
                        translationZ = dip(-2).toFloat()
                    }.lparams(matchParent, dip(Dimens.MAIN_DUMMY_HEIGHT)) {
                        topToTop = PARENT_ID
                    }

                    textView {

                        id = Id.tvContent
                        bindText(ui.owner, homeViewModel.title)
                        textAlignment = TEXT_ALIGNMENT_CENTER
                        textColor = R.color.main_grey
                        textSize = Dimens.TITLE_DESC_SIZE
                    }.lparams(matchParent, wrapContent) {
                        setMargins(
                            dip(Dimens.SPACING),
                            dip(Dimens.DOUBLE_SPACING),
                            dip(Dimens.SPACING),
                            0
                        )
                        topToBottom = Id.dummyView
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
                        topToBottom = Id.tvContent
                    }

                    ui.owner.content = frameLayout {
                        id = Id.content
                        backgroundColor = R.color.content
                        isFocusable = false
                        isNestedScrollingEnabled = false
                        scrollBarSize = 0
                        bindFragment(ui.owner, homeViewModel.fragment)
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

            ui.owner.swipe = this
        }
    }
}