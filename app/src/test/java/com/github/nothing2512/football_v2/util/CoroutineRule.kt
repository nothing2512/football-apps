package com.github.nothing2512.football_v2.util

import com.github.nothing2512.football_v2.utils.Constants
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.ContinuationInterceptor

@Suppress("DeferredResultUnused")
@ExperimentalCoroutinesApi
class CoroutineRule : TestWatcher(), TestCoroutineScope by TestCoroutineScope() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(this.coroutineContext[ContinuationInterceptor] as CoroutineDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }

    fun runAsync(block: suspend (ctx: CoroutineScope) -> Unit) {
        async {
            delay(Constants.SERVICE_LATENCY_IN_MILLIS)
            block.invoke(this)
        }
    }
}