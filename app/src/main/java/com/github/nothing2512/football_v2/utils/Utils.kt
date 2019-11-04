package com.github.nothing2512.football_v2.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ShimmerFrameLayout.start() {
    show()
    startShimmer()
}

fun ShimmerFrameLayout.stop() {
    hide()
    stopShimmer()
}

fun <VDB : ViewDataBinding> Activity.getBinding(layout: Int): VDB =
    DataBindingUtil.setContentView(this, layout)

fun <VDB : ViewDataBinding> getBinding(
    inflater: LayoutInflater,
    layout: Int,
    parent: ViewGroup?
): VDB =
    DataBindingUtil.inflate(inflater, layout, parent, false)

fun <VDB : ViewDataBinding> getBinding(layout: Int, parent: ViewGroup?): VDB =
    DataBindingUtil.inflate(LayoutInflater.from(parent?.context), layout, parent, false)

fun <T> ViewModel.launchMain(block: suspend CoroutineScope.() -> T) {
    viewModelScope.launch {
        withContext(Dispatchers.Main) {
            block.invoke(this)
        }
    }
}

fun <T> launchMain(block: suspend CoroutineScope.() -> T) {
    CoroutineScope(Dispatchers.Main).launch {
        block.invoke(this)
    }
}