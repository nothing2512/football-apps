package com.github.nothing2512.football_v2.binding

data class TeamBinding(
    val onWebClick: () -> Unit,
    val onFacebookClick: () -> Unit,
    val onTwitterClick: () -> Unit,
    val onInstagramClick: () -> Unit,
    val onYoutubeClick: () -> Unit,
    val onDescriptionClick: () -> Unit
)