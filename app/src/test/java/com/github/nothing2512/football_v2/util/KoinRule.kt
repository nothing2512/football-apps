package com.github.nothing2512.football_v2.util

import com.github.nothing2512.football_v2.di.appModule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class KoinRule : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        startKoin { modules(appModule) }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        stopKoin()
    }
}