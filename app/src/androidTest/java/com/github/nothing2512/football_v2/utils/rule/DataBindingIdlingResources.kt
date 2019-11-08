package com.github.nothing2512.football_v2.utils.rule

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.test.espresso.IdlingResource
import androidx.test.rule.ActivityTestRule
import java.util.*

@Suppress("LiftReturnOrAssignment")
class DataBindingIdlingResources(
    private val activity: ActivityTestRule<*>
) : IdlingResource {

    private val idlingCallbacks = mutableListOf<IdlingResource.ResourceCallback>()
    private val id = UUID.randomUUID().toString()
    private var wasNotIdle = false

    override fun getName(): String = "DataBinding $id"

    override fun isIdleNow(): Boolean {

        val idle = !getBindings().any { it.hasPendingBindings() }
        if (idle) {

            if (wasNotIdle) idlingCallbacks.forEach { it.onTransitionToIdle() }
            wasNotIdle = false
        } else {

            wasNotIdle = true
            activity.activity.findViewById<View>(android.R.id.content)
                .postDelayed({ isIdleNow }, 16)
        }

        return idle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        idlingCallbacks.add(callback)
    }

    private fun getBindings(): List<ViewDataBinding> {
        return (activity.activity as? FragmentActivity)
            ?.supportFragmentManager
            ?.fragments
            ?.mapNotNull {
                it.view?.let { view ->
                    DataBindingUtil.getBinding<ViewDataBinding>(
                        view
                    )
                }
            } ?: emptyList()
    }

}