package com.github.nothing2512.football_v2.testing

import com.github.nothing2512.football_v2.utils.AppExecutors
import java.util.concurrent.Executor


class InstantAppExecutors : AppExecutors(
    instant,
    instant,
    instant
) {
    companion object {
        private val instant = Executor { it.run() }
    }
}