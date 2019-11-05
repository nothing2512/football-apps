package com.github.nothing2512.football_v2.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import coil.api.load
import coil.request.LoadRequestBuilder
import coil.transform.BlurTransformation
import com.github.nothing2512.football_v2.R

fun FrameLayout.bindFragment(
    activity: FragmentActivity,
    data: LiveData<Fragment>
) {
    data.observe(activity, Observer { fragment ->
        fragment?.let {
            activity.supportFragmentManager
                .beginTransaction()
                .replace(this.id, it)
                .commit()
        }
    })
}

fun <T> TextView.bindText(owner: LifecycleOwner, liveData: LiveData<T>, data: T.() -> String) {
    liveData.observe(owner, Observer { this.text = data.invoke(it) })
}

fun TextView.bindText(owner: LifecycleOwner, data: LiveData<String>) {
    data.observe(owner, Observer { this.text = it })
}

fun <T> ImageView.bindImage(owner: LifecycleOwner, liveData: LiveData<T>, data: T.() -> Any) {
    liveData.observe(owner, Observer {
        bindImage(data.invoke(it), false)
    })
}

fun ImageView.bindImage(source: Any?, blur: Boolean) {
    val builder: LoadRequestBuilder.() -> Unit = if (!blur) {
        { placeholder(R.mipmap.ic_launcher) }
    } else {
        {
            placeholder(R.mipmap.ic_launcher)
            transformations(BlurTransformation(context, 50f))
        }
    }

    when (source) {
        is String -> load(source, builder = builder)
        is Int -> load(source, builder = builder)
        is Uri -> load(source, builder = builder)
        is Drawable -> load(source, builder = builder)
        else -> load(R.mipmap.ic_launcher, builder = builder)
    }
}