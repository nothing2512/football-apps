package com.github.nothing2512.football_v2.utils.repository

import com.github.nothing2512.football_v2.data.source.anko.DatabaseHelper
import com.github.nothing2512.football_v2.repositories.EventRepository
import com.github.nothing2512.football_v2.testing.InstantAppExecutors
import com.github.nothing2512.football_v2.utils.data.source.NetworkServiceUtil

class EventRepositoryUtil : EventRepository(
    InstantAppExecutors(),
    DatabaseHelper(null),
    NetworkServiceUtil()
)