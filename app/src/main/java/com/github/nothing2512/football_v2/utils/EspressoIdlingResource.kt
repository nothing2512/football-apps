package com.github.nothing2512.football_v2.utils

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.idling.CountingIdlingResource
import com.github.nothing2512.football_v2.utils.resources.Constants

object EspressoIdlingResource {

    val count = CountingIdlingResource(Constants.IDLING)

    fun <T> handle(onHandle: () -> LiveData<T>): LiveData<T> {

        val liveData = MutableLiveData<T>()

        count.increment()
        Handler().postDelayed({
            onHandle.invoke().observeForever { liveData.postValue(it) }
            if (!count.isIdleNow)
                count.decrement()
        }, Constants.SERVICE_LATENCY_IN_MILLIS)

        return liveData
    }
}