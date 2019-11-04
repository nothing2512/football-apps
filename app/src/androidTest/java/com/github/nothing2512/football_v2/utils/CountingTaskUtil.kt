package com.github.nothing2512.football_v2.utils

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.TimeUnit

fun <T> MutableLiveData<T>.post(task: CountingTaskExecutorRule, data: T) {
    this.postValue(data)
    task.drainTasks(Constants.SERVICE_LATENCY_IN_MILLIS.toInt(), TimeUnit.MILLISECONDS)
}