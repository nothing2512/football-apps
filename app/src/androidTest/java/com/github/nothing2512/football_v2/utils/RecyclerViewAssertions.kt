package com.github.nothing2512.football_v2.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertNotNull

class RecyclerViewAssertions(private val expectedCount: Int?) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {

        if (noViewFoundException != null) throw noViewFoundException

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertNotNull(adapter)
        ViewMatchers.assertThat<Int>(adapter!!.itemCount, CoreMatchers.`is`<Int>(expectedCount))
    }

}