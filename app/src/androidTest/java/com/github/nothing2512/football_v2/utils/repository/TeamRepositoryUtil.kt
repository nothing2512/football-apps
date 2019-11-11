package com.github.nothing2512.football_v2.utils.repository

import androidx.test.core.app.ApplicationProvider
import com.github.nothing2512.football_v2.data.source.local.DatabaseHelper
import com.github.nothing2512.football_v2.repositories.TeamRepository
import com.github.nothing2512.football_v2.testing.InstantAppExecutors
import com.github.nothing2512.football_v2.utils.data.source.DatabaseUtil
import com.github.nothing2512.football_v2.utils.data.source.NetworkServiceUtil

class TeamRepositoryUtil : TeamRepository(
    InstantAppExecutors(),
    DatabaseHelper(DatabaseUtil(ApplicationProvider.getApplicationContext())),
    NetworkServiceUtil()
)