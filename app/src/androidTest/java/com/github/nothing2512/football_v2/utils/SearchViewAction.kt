package com.github.nothing2512.football_v2.utils

import android.view.View
import android.widget.SearchView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

class SearchViewAction(private val text: String) : ViewAction {
    override fun getDescription() = "type text"

    override fun getConstraints(): Matcher<View> =
        ViewMatchers.isAssignableFrom(SearchView::class.java)

    override fun perform(uiController: UiController?, view: View?) {
        val v = view as SearchView
        v.isSubmitButtonEnabled = true
        v.setQuery(text, true)
        v.isIconified = false
    }

}