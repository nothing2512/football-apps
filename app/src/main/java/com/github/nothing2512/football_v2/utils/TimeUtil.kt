package com.github.nothing2512.football_v2.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    @SuppressLint("SimpleDateFormat")
    fun format(date: String, time: String): List<String> {

        try {

            val timeSplit = time.split("+")
            val timeTemp = timeSplit[0]
            val gmt = 7 - timeSplit[1].split(":")[0].toInt()
            val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
            val c = Calendar.getInstance()

            c.time = sdf.parse("$date $timeTemp")
            c.add(Calendar.HOUR, gmt)

            return sdf.format(c.time).split(" ")
        } catch (e: IndexOutOfBoundsException) {

            return try {
                val gmt = 7
                val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                val c = Calendar.getInstance()

                c.time = sdf.parse("$date $time")
                c.add(Calendar.HOUR, gmt)

                sdf.format(c.time).split(" ")
            } catch (e: ParseException) {

                listOf(date, time)
            }
        }
    }
}