package com.github.nothing2512.football_v2.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import coil.api.load
import coil.transform.BlurTransformation
import com.github.nothing2512.football_v2.R

@BindingAdapter("source", "blur", requireAll = false)
fun ImageView.bind(source: Any, blur: Boolean) {

    if (!blur) {
        when (source) {
            is String -> load(source) { placeholder(R.mipmap.ic_launcher) }
            is Int -> load(source) { placeholder(R.mipmap.ic_launcher) }
            is Uri -> load(source) { placeholder(R.mipmap.ic_launcher) }
            is Drawable -> load(source) { placeholder(R.mipmap.ic_launcher) }
        }
    } else {

        when (source) {
            is String -> load(source) {
                placeholder(R.mipmap.ic_launcher)
                transformations(BlurTransformation(this@bind.context, 50f))
            }
            is Int -> load(source) {
                placeholder(R.mipmap.ic_launcher)
                transformations(BlurTransformation(this@bind.context, 50f))
            }
            is Uri -> load(source) {
                placeholder(R.mipmap.ic_launcher)
                transformations(BlurTransformation(this@bind.context, 50f))
            }
            is Drawable -> load(source) {
                placeholder(R.mipmap.ic_launcher)
                transformations(BlurTransformation(this@bind.context, 50f))
            }
        }
    }
}

@BindingAdapter("activity", "fragment", requireAll = false)
fun FrameLayout.bindFragment(activity: FragmentActivity, fragment: Fragment?) {
    fragment?.let {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(this.id, it)
            .commit()
    }
}
