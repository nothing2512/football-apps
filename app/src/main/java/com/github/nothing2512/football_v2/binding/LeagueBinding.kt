package com.github.nothing2512.football_v2.binding

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.github.nothing2512.football_v2.data.source.local.entity.LeagueEntity

data class LeagueBinding(
    val strLeague: String,
    val intFormedYear: String,
    val dateFirstEvent: String,
    val strGender: String,
    val strCountry: String,
    val onWebsitePress: () -> Unit,
    val onFacebookPress: () -> Unit,
    val onTwitterPress: () -> Unit,
    val onYoutubePress: () -> Unit,
    val strBadge: String,
    val onDescriptionClicked: () -> Unit,
    val onBackPress: () -> Unit
) {

    companion object {

        private fun getLink(url: String?): String {
            val linkReg = Regex("(http|https)://(\\w+)")
            return if (linkReg.containsMatchIn(url ?: "")) "$url"
            else "https://$url"
        }

        private fun openBrowser(context: Context, url: String?) {

            try {
                context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(getLink(url))
                })
            } catch (_: Exception) {
                Toast.makeText(context, "Url doesnt exist", Toast.LENGTH_SHORT).show()
            } catch (_: ActivityNotFoundException) {
                Toast.makeText(context, "Cannot open browser", Toast.LENGTH_SHORT).show()
            }
        }

        fun parse(
            leagueEntity: LeagueEntity?,
            onDescriptionClicked: () -> Unit,
            onBackPress: () -> Unit,
            context: Context
        ) = LeagueBinding(
            leagueEntity?.strLeague ?: "",
            leagueEntity?.intFormedYear ?: "",
            leagueEntity?.dateFirstEvent ?: "",
            leagueEntity?.strGender ?: "",
            leagueEntity?.strCountry ?: "",
            { openBrowser(context, leagueEntity?.strWebsite) },
            { openBrowser(context, leagueEntity?.strFacebook) },
            { openBrowser(context, leagueEntity?.strTwitter) },
            { openBrowser(context, leagueEntity?.strYoutube) },
            leagueEntity?.strBadge ?: "",
            onDescriptionClicked,
            onBackPress
        )
    }
}