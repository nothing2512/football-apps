package com.github.nothing2512.football_v2.utils.repository

import com.github.nothing2512.football_v2.data.source.local.DatabaseHelper
import com.github.nothing2512.football_v2.repositories.LeagueRepository
import com.github.nothing2512.football_v2.testing.InstantAppExecutors
import com.github.nothing2512.football_v2.utils.data.source.NetworkServiceUtil

class LeagueRepositoryUtil : LeagueRepository(
    InstantAppExecutors(),
    DatabaseHelper(null),
    NetworkServiceUtil()
)