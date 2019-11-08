package com.github.nothing2512.football_v2.utils

import android.os.Handler
import android.os.Looper
import com.github.nothing2512.football_v2.testing.OpenForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@OpenForTesting
class AppExecutors
constructor(
    val diskIO: Executor = Executors.newSingleThreadExecutor(),
    val mainThread: Executor = MainThreadExecutor()
) {

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

}
